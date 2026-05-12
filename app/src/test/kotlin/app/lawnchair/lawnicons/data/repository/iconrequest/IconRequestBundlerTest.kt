/*
 * Copyright 2026 Lawnchair Launcher
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package app.lawnchair.lawnicons.data.repository.iconrequest

import android.graphics.drawable.Drawable
import app.lawnchair.lawnicons.data.model.Component
import app.lawnchair.lawnicons.data.model.SystemIconInfo
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class IconRequestBundlerTest {

    @Test
    fun `normalizeFileName converts to lowercase`() {
        val result = IconRequestBundler.normalizeFileName("MyApplication")

        assertEquals("myapplication", result)
    }

    @Test
    fun `normalizeFileName replaces spaces with underscores`() {
        val result = IconRequestBundler.normalizeFileName("My App Name")

        assertEquals("my_app_name", result)
    }

    @Test
    fun `normalizeFileName removes special characters`() {
        val result = IconRequestBundler.normalizeFileName("App & Co. - Premium™")

        assertEquals("app___co____premium", result)
    }

    @Test
    fun `normalizeFileName removes diacritics`() {
        val result = IconRequestBundler.normalizeFileName("Café Naïve")

        assertEquals("cafe_naive", result)
    }

    @Test
    fun `normalizeFileName handles digits correctly`() {
        val result = IconRequestBundler.normalizeFileName("App 123")

        assertEquals("app_123", result)
    }

    @Test
    fun `normalizeFileName returns unnamed_icon for blank input`() {
        val result = IconRequestBundler.normalizeFileName("   ")

        assertEquals("unnamed_icon", result)
    }

    @Test
    fun `normalizeFileName returns unnamed_icon for empty string`() {
        val result = IconRequestBundler.normalizeFileName("")

        assertEquals("unnamed_icon", result)
    }

    @Test
    fun `normalizeFileName handles special symbols only string`() {
        val result = IconRequestBundler.normalizeFileName("@#$%&*()")

        assertEquals("unnamed_icon", result)
    }

    @Test
    fun `normalizeFileName handles leading and trailing underscores`() {
        val result = IconRequestBundler.normalizeFileName("__app_name__")

        assertEquals("app_name", result)
    }

    @Test
    fun `normalizeFileName handles consecutive underscores`() {
        val result = IconRequestBundler.normalizeFileName("app___name")

        assertEquals("app___name", result)
    }

    @Test
    fun `normalizeFileName with Unicode characters`() {
        val result = IconRequestBundler.normalizeFileName("Приложение")

        // Unicode gets normalized to ASCII approximation
        assertEquals("unnamed_icon", result)
    }

    @Test
    fun `createUniqueIconInfoList with empty list returns empty list`() {
        val result = IconRequestBundler.createUniqueIconInfoList(emptyList())

        assertEquals(emptyList<IconRequestBundler.UniqueIconInfo>(), result)
    }

    @Test
    fun `createUniqueIconInfoList with single item preserves name`() {
        val mockDrawable = mock(Drawable::class.java)
        val component = Component("com.app", "com.app.Activity")
        val icon = SystemIconInfo(
            drawable = mockDrawable,
            label = "My App",
            component = component,
        )

        val result = IconRequestBundler.createUniqueIconInfoList(listOf(icon))

        assertEquals(1, result.size)
        assertEquals("my_app", result[0].drawableName)
        assertEquals(icon, result[0].iconInfo)
    }

    @Test
    fun `createUniqueIconInfoList deduplicates with numeric suffix`() {
        val mockDrawable = mock(Drawable::class.java)
        val component1 = Component("com.app1", "com.app1.Activity")
        val component2 = Component("com.app2", "com.app2.Activity")
        val icon1 = SystemIconInfo(
            drawable = mockDrawable,
            label = "Same Name",
            component = component1,
        )
        val icon2 = SystemIconInfo(
            drawable = mockDrawable,
            label = "Same Name",
            component = component2,
        )

        val result = IconRequestBundler.createUniqueIconInfoList(listOf(icon1, icon2))

        assertEquals(2, result.size)
        assertEquals("same_name", result[0].drawableName)
        assertEquals("same_name_1", result[1].drawableName)
    }

    @Test
    fun `createUniqueIconInfoList handles three collisions`() {
        val mockDrawable = mock(Drawable::class.java)
        val icons = (1..3).map { i ->
            SystemIconInfo(
                drawable = mockDrawable,
                label = "Collide",
                component = Component("com.app$i", "com.app$i.Activity"),
            )
        }

        val result = IconRequestBundler.createUniqueIconInfoList(icons)

        assertEquals(3, result.size)
        assertEquals("collide", result[0].drawableName)
        assertEquals("collide_1", result[1].drawableName)
        assertEquals("collide_2", result[2].drawableName)
    }

    @Test
    fun `createUniqueIconInfoList handles partial collisions`() {
        val mockDrawable = mock(Drawable::class.java)
        val component1 = Component("com.app1", "com.app1.Activity")
        val component2 = Component("com.app2", "com.app2.Activity")
        val component3 = Component("com.app3", "com.app3.Activity")
        val icon1 = SystemIconInfo(
            drawable = mockDrawable,
            label = "App A",
            component = component1,
        )
        val icon2 = SystemIconInfo(
            drawable = mockDrawable,
            label = "App A",
            component = component2,
        )
        val icon3 = SystemIconInfo(
            drawable = mockDrawable,
            label = "App B",
            component = component3,
        )

        val result = IconRequestBundler.createUniqueIconInfoList(listOf(icon1, icon2, icon3))

        assertEquals(3, result.size)
        assertEquals("app_a", result[0].drawableName)
        assertEquals("app_a_1", result[1].drawableName)
        assertEquals("app_b", result[2].drawableName)
    }

    @Test
    fun `createUniqueIconInfoList normalizes different variations to same name`() {
        val mockDrawable = mock(Drawable::class.java)
        val component1 = Component("com.app1", "com.app1.Activity")
        val component2 = Component("com.app2", "com.app2.Activity")
        val icon1 = SystemIconInfo(
            drawable = mockDrawable,
            label = "café",
            component = component1,
        )
        val icon2 = SystemIconInfo(
            drawable = mockDrawable,
            label = "CAFÉ",
            component = component2,
        )

        val result = IconRequestBundler.createUniqueIconInfoList(listOf(icon1, icon2))

        assertEquals(2, result.size)
        assertEquals("cafe", result[0].drawableName)
        assertEquals("cafe_1", result[1].drawableName)
    }

    @Test
    fun `createUniqueIconInfoList preserves icon info correctly`() {
        val mockDrawable = mock(Drawable::class.java)
        val component = Component("com.example", "com.example.Activity")
        val originalIcon = SystemIconInfo(
            drawable = mockDrawable,
            label = "Original",
            component = component,
        )

        val result = IconRequestBundler.createUniqueIconInfoList(listOf(originalIcon))

        assertEquals(originalIcon, result[0].iconInfo)
        assertEquals("original", result[0].drawableName)
    }
}
