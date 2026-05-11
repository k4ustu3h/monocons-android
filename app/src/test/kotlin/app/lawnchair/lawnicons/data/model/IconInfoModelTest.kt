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

package app.lawnchair.lawnicons.data.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class IconInfoModelTest {

    @Test
    fun `default constructor creates empty model`() {
        val model = IconInfoModel()

        assertTrue(model.iconInfo.isEmpty())
        assertEquals(0, model.iconCount)
    }

    @Test
    fun `constructor with explicit values sets fields correctly`() {
        val iconInfoList = listOf(
            IconInfo(
                drawableName = "icon1",
                componentNames = listOf(LabelAndComponent("App 1", "com.app1/Activity")),
                drawableId = 1,
            ),
            IconInfo(
                drawableName = "icon2",
                componentNames = listOf(LabelAndComponent("App 2", "com.app2/Activity")),
                drawableId = 2,
            ),
        )

        val model = IconInfoModel(
            iconInfo = iconInfoList,
            iconCount = 2,
        )

        assertEquals(2, model.iconInfo.size)
        assertEquals(2, model.iconCount)
        assertEquals("icon1", model.iconInfo[0].drawableName)
        assertEquals("icon2", model.iconInfo[1].drawableName)
    }

    @Test
    fun `data class equality works correctly`() {
        val iconInfo = IconInfo(
            drawableName = "icon",
            componentNames = listOf(LabelAndComponent("App", "com.app/Activity")),
            drawableId = 1,
        )

        val model1 = IconInfoModel(iconInfo = listOf(iconInfo), iconCount = 1)
        val model2 = IconInfoModel(iconInfo = listOf(iconInfo), iconCount = 1)
        val model3 = model1.copy(iconCount = 5)

        assertEquals(model1, model2)
        assertNotEquals(model1, model3)
    }

    @Test
    fun `copy function works correctly`() {
        val iconInfo = IconInfo(
            drawableName = "icon",
            componentNames = listOf(LabelAndComponent("App", "com.app/Activity")),
            drawableId = 1,
        )

        val original = IconInfoModel(iconInfo = listOf(iconInfo), iconCount = 1)
        val copied = original.copy(iconCount = 10)

        assertEquals(1, original.iconCount)
        assertEquals(10, copied.iconCount)
        assertEquals(original.iconInfo, copied.iconInfo)
    }

    @Test
    fun `iconCount can differ from list size`() {
        // Note: This tests the current behavior where iconCount is independent
        // In practice, these should match, but the model doesn't enforce this
        val model = IconInfoModel(iconInfo = emptyList(), iconCount = 100)

        assertTrue(model.iconInfo.isEmpty())
        assertEquals(100, model.iconCount)
    }

    @Test
    fun `handles large icon list`() {
        val largeList = (1..100).map { i ->
            IconInfo(
                drawableName = "icon_$i",
                componentNames = listOf(LabelAndComponent("App $i", "com.app$i/Activity")),
                drawableId = i,
            )
        }

        val model = IconInfoModel(iconInfo = largeList, iconCount = 100)

        assertEquals(100, model.iconInfo.size)
        assertEquals(100, model.iconCount)
        assertEquals("icon_1", model.iconInfo.first().drawableName)
        assertEquals("icon_100", model.iconInfo.last().drawableName)
    }
}
