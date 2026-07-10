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

package k4ustu3h.monocons.data.model

import android.graphics.drawable.Drawable
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class IconRequestModelTest {

    @Test
    fun `IconRequestModel constructor sets list and iconCount`() {
        val mockDrawable = mock(Drawable::class.java)
        val component = Component("com.example", "com.example.MainActivity")

        val systemIconInfoList = listOf(
            SystemIconInfo(
                drawable = mockDrawable,
                label = "App 1",
                component = component,
            ),
            SystemIconInfo(
                drawable = mockDrawable,
                label = "App 2",
                component = component,
            ),
        )

        val model = IconRequestModel(
            list = systemIconInfoList,
            iconCount = 2,
        )

        assertEquals(2, model.list.size)
        assertEquals(2, model.iconCount)
        assertEquals("App 1", model.list[0].label)
        assertEquals("App 2", model.list[1].label)
    }

    @Test
    fun `IconRequestModel handles empty list`() {
        val model = IconRequestModel(
            list = emptyList(),
            iconCount = 0,
        )

        assertTrue(model.list.isEmpty())
        assertEquals(0, model.iconCount)
    }

    @Test
    fun `IconRequestModel data class equality works correctly`() {
        val mockDrawable = mock(Drawable::class.java)
        val component = Component("com.example", "com.example.MainActivity")

        val systemIconInfo = SystemIconInfo(
            drawable = mockDrawable,
            label = "App",
            component = component,
        )

        val model1 = IconRequestModel(list = listOf(systemIconInfo), iconCount = 1)
        val model2 = IconRequestModel(list = listOf(systemIconInfo), iconCount = 1)
        val model3 = model1.copy(iconCount = 2)

        assertEquals(model1, model2)
        assertNotEquals(model1, model3)
    }

    @Test
    fun `IconRequestModel copy function works correctly`() {
        val mockDrawable = mock(Drawable::class.java)
        val component = Component("com.example", "com.example.MainActivity")

        val systemIconInfo = SystemIconInfo(
            drawable = mockDrawable,
            label = "App",
            component = component,
        )

        val original = IconRequestModel(list = listOf(systemIconInfo), iconCount = 1)
        val copied = original.copy(iconCount = 5)

        assertEquals(1, original.iconCount)
        assertEquals(5, copied.iconCount)
        assertEquals(original.list, copied.list)
    }

    @Test
    fun `IconRequestModel iconCount can differ from list size`() {
        // Note: This tests the current behavior where iconCount is independent
        // In practice, these should match, but the model doesn't enforce this
        val model = IconRequestModel(
            list = emptyList(),
            iconCount = 10,
        )

        assertTrue(model.list.isEmpty())
        assertEquals(10, model.iconCount)
    }

    @Test
    fun `IconRequestData stores zipFile and componentListString`() {
        val tempFile = java.io.File.createTempFile("test", ".zip")
        tempFile.deleteOnExit()

        val requestData = IconRequestData(
            zipFile = tempFile,
            componentListString = "com.app1/.Activity1\ncom.app2/.Activity2",
        )

        assertEquals(tempFile, requestData.zipFile)
        assertEquals("com.app1/.Activity1\ncom.app2/.Activity2", requestData.componentListString)
    }

    @Test
    fun `IconRequestData data class equality works correctly`() {
        val tempFile = java.io.File.createTempFile("test", ".zip")
        tempFile.deleteOnExit()

        val data1 = IconRequestData(zipFile = tempFile, componentListString = "test")
        val data2 = IconRequestData(zipFile = tempFile, componentListString = "test")
        val data3 = data1.copy(componentListString = "different")

        assertEquals(data1, data2)
        assertNotEquals(data1, data3)
    }
}
