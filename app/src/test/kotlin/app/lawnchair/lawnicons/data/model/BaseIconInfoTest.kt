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

import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertThrows
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BaseIconInfoTest {

    private val json = Json { ignoreUnknownKeys = true }

    @Test
    fun `label returns first component label when components exist`() {
        val testIconInfo = object : BaseIconInfo {
            override val componentNames = listOf(
                LabelAndComponent("First App", "com.first/Activity"),
                LabelAndComponent("Second App", "com.second/Activity"),
            )
        }

        assertEquals("First App", testIconInfo.label)
    }

    @Test
    fun `label returns empty string when no components exist`() {
        val testIconInfo = object : BaseIconInfo {
            override val componentNames = emptyList<LabelAndComponent>()
        }

        assertEquals("", testIconInfo.label)
    }

    @Test
    fun `getFirstLabelAndComponent returns first label and component`() {
        val testIconInfo = object : BaseIconInfo {
            override val componentNames = listOf(
                LabelAndComponent("App One", "com.one/Activity"),
                LabelAndComponent("App Two", "com.two/Activity"),
            )
        }

        val result = testIconInfo.getFirstLabelAndComponent()

        assertEquals("App One", result.label)
        assertEquals("com.one", result.component.packageName)
    }

    @Test
    fun `getFirstLabelAndComponent returns empty values when no components exist`() {
        val testIconInfo = object : BaseIconInfo {
            override val componentNames = emptyList<LabelAndComponent>()
        }

        val result = testIconInfo.getFirstLabelAndComponent()

        assertEquals("", result.label)
        assertEquals("", result.component.packageName)
        assertEquals("", result.component.className)
    }

    @Test
    fun `LabelAndComponent equality works correctly`() {
        val lc1 = LabelAndComponent("App", "com.app/Activity")
        val lc2 = LabelAndComponent("App", "com.app/Activity")
        val lc3 = LabelAndComponent("Different App", "com.app/Activity")

        assertEquals(lc1, lc2)
        assertNotEquals(lc1, lc3)
    }

    @Test
    fun `LabelAndComponent serialization round trip works`() {
        val original = LabelAndComponent("Test App", "com.test/MainActivity")
        val jsonString = json.encodeToString(original)
        val deserialized = json.decodeFromString<LabelAndComponent>(jsonString)

        assertEquals(original.label, deserialized.label)
    }

    @Test
    fun `LabelAndComponent string constructor throws for componentName without slash`() {
        assertThrows(NullPointerException::class.java) {
            LabelAndComponent("App", Component.unflattenFromString("invalid_no_slash")!!)
        }
    }
}
