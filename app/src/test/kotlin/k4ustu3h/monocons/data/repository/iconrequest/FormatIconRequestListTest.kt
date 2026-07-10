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

package k4ustu3h.monocons.data.repository.iconrequest

import android.graphics.drawable.Drawable
import k4ustu3h.monocons.data.model.Component
import k4ustu3h.monocons.data.model.SystemIconInfo
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FormatIconRequestListTest {

    @Test
    fun `formatIconRequestList with empty list returns empty string`() {
        val result = formatIconRequestList(emptyList())

        assertEquals("", result)
    }

    @Test
    fun `formatIconRequestList with single entry formats correctly`() {
        val mockDrawable = mock(Drawable::class.java)
        val component = Component("com.example", "com.example.MainActivity")
        val systemIconInfo = SystemIconInfo(
            drawable = mockDrawable,
            label = "Example App",
            component = component,
        )

        val result = formatIconRequestList(listOf(systemIconInfo))

        assertEquals("Example App\ncom.example/com.example.MainActivity", result)
    }

    @Test
    fun `formatIconRequestList with multiple entries separates with double newline`() {
        val mockDrawable = mock(Drawable::class.java)
        val component1 = Component("com.app1", "com.app1.Main")
        val component2 = Component("com.app2", "com.app2.Main")
        val icon1 = SystemIconInfo(
            drawable = mockDrawable,
            label = "App One",
            component = component1,
        )
        val icon2 = SystemIconInfo(
            drawable = mockDrawable,
            label = "App Two",
            component = component2,
        )

        val result = formatIconRequestList(listOf(icon1, icon2))

        assertEquals(
            "App One\ncom.app1/com.app1.Main\n\nApp Two\ncom.app2/com.app2.Main",
            result,
        )
    }

    @Test
    fun `formatIconRequestList handles labels with special characters`() {
        val mockDrawable = mock(Drawable::class.java)
        val component = Component("com.special", "com.special.Activity")
        val systemIconInfo = SystemIconInfo(
            drawable = mockDrawable,
            label = "App & Co. - Special™",
            component = component,
        )

        val result = formatIconRequestList(listOf(systemIconInfo))

        assertEquals("App & Co. - Special™\ncom.special/com.special.Activity", result)
    }

    @Test
    fun `formatIconRequestList preserves Unicode labels`() {
        val mockDrawable = mock(Drawable::class.java)
        val component = Component("com.unicode", "com.unicode.Activity")
        val systemIconInfo = SystemIconInfo(
            drawable = mockDrawable,
            label = "Приложение",
            component = component,
        )

        val result = formatIconRequestList(listOf(systemIconInfo))

        assertEquals("Приложение\ncom.unicode/com.unicode.Activity", result)
    }

    @Test
    fun `formatIconRequestList handles relative class names`() {
        val mockDrawable = mock(Drawable::class.java)
        val component = Component("com.relative", "com.relative.Activity")
        val systemIconInfo = SystemIconInfo(
            drawable = mockDrawable,
            label = "Relative",
            component = component,
        )

        val result = formatIconRequestList(listOf(systemIconInfo))

        assertEquals("Relative\ncom.relative/com.relative.Activity", result)
    }
}
