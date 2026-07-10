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
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SystemIconInfoTest {

    @Test
    fun `constructor initializes all fields correctly`() {
        val mockDrawable = mock(Drawable::class.java)
        val component = Component("com.example", "com.example.MainActivity")

        val systemIconInfo = SystemIconInfo(
            drawable = mockDrawable,
            label = "Example App",
            component = component,
        )

        assertEquals(mockDrawable, systemIconInfo.drawable)
        assertEquals("Example App", systemIconInfo.label)
        assertEquals(component, systemIconInfo.component)
    }

    @Test
    fun `label defaults to empty string when not provided`() {
        val mockDrawable = mock(Drawable::class.java)
        val component = Component("com.example", "com.example.Activity")

        val systemIconInfo = SystemIconInfo(
            drawable = mockDrawable,
            component = component,
        )

        assertEquals("", systemIconInfo.label)
    }

    @Test
    fun `componentNames list contains exactly one LabelAndComponent`() {
        val mockDrawable = mock(Drawable::class.java)
        val component = Component("com.example", "com.example.MainActivity")

        val systemIconInfo = SystemIconInfo(
            drawable = mockDrawable,
            label = "Test App",
            component = component,
        )

        assertEquals(1, systemIconInfo.componentNames.size)
        assertEquals("Test App", systemIconInfo.componentNames[0].label)
        assertEquals(component, systemIconInfo.componentNames[0].component)
    }

    @Test
    fun `inherits label from BaseIconInfo correctly`() {
        val mockDrawable = mock(Drawable::class.java)
        val component = Component("com.app", "com.app.Main")

        val systemIconInfo: BaseIconInfo = SystemIconInfo(
            drawable = mockDrawable,
            label = "Inherited Label",
            component = component,
        )

        assertEquals("Inherited Label", systemIconInfo.label)
    }

    @Test
    fun `getFirstLabelAndComponent returns correct data`() {
        val mockDrawable = mock(Drawable::class.java)
        val component = Component("com.example", "com.example.Activity")

        val systemIconInfo = SystemIconInfo(
            drawable = mockDrawable,
            label = "First Label",
            component = component,
        )

        val firstLabelAndComponent = systemIconInfo.getFirstLabelAndComponent()

        assertEquals("First Label", firstLabelAndComponent.label)
        assertEquals(component, firstLabelAndComponent.component)
    }

    @Test
    fun `data class equality works correctly with same drawable`() {
        val mockDrawable = mock(Drawable::class.java)
        val component = Component("com.example", "com.example.Activity")

        val info1 = SystemIconInfo(
            drawable = mockDrawable,
            label = "App",
            component = component,
        )
        val info2 = SystemIconInfo(
            drawable = mockDrawable,
            label = "App",
            component = component,
        )
        val info3 = info1.copy(label = "Different App")

        assertEquals(info1, info2)
        assertNotEquals(info1, info3)
    }

    @Test
    fun `data class equality detects different drawables`() {
        val mockDrawable1 = mock(Drawable::class.java)
        val mockDrawable2 = mock(Drawable::class.java)
        val component = Component("com.example", "com.example.Activity")

        val info1 = SystemIconInfo(
            drawable = mockDrawable1,
            label = "App",
            component = component,
        )
        val info2 = SystemIconInfo(
            drawable = mockDrawable2,
            label = "App",
            component = component,
        )

        assertNotEquals(info1, info2)
    }

    @Test
    fun `copy function works correctly`() {
        val mockDrawable = mock(Drawable::class.java)
        val component = Component("com.example", "com.example.Activity")

        val original = SystemIconInfo(
            drawable = mockDrawable,
            label = "Original",
            component = component,
        )
        val copied = original.copy(label = "Copied")

        assertEquals("Original", original.label)
        assertEquals("Copied", copied.label)
        assertEquals(original.drawable, copied.drawable)
        assertEquals(original.component, copied.component)
    }

    @Test
    fun `componentNames is derived from label and component`() {
        val mockDrawable = mock(Drawable::class.java)
        val component = Component("com.test", "com.test.TestActivity")

        val systemIconInfo = SystemIconInfo(
            drawable = mockDrawable,
            label = "Test Label",
            component = component,
        )

        val labelAndComponent = systemIconInfo.componentNames.first()
        assertEquals("Test Label", labelAndComponent.label)
        assertEquals("com.test", labelAndComponent.component.packageName)
        assertEquals("com.test.TestActivity", labelAndComponent.component.className)
    }

    @Test
    fun `copy with new label updates componentNames accordingly`() {
        val mockDrawable = mock(Drawable::class.java)
        val component = Component("com.example", "com.example.Activity")

        val original = SystemIconInfo(
            drawable = mockDrawable,
            label = "Original Label",
            component = component,
        )
        val copied = original.copy(label = "Updated Label")

        // The copy creates a new instance so componentNames is re-initialized
        assertEquals("Updated Label", copied.label)
        assertEquals("Updated Label", copied.componentNames.first().label)
        // Original is unaffected
        assertEquals("Original Label", original.componentNames.first().label)
    }

    @Test
    fun `copy with new component updates componentNames accordingly`() {
        val mockDrawable = mock(Drawable::class.java)
        val originalComponent = Component("com.original", "com.original.Activity")
        val newComponent = Component("com.new", "com.new.Activity")

        val original = SystemIconInfo(
            drawable = mockDrawable,
            label = "App",
            component = originalComponent,
        )
        val copied = original.copy(component = newComponent)

        assertEquals(newComponent, copied.component)
        assertEquals(newComponent, copied.componentNames.first().component)
        // Original is unaffected
        assertEquals(originalComponent, original.componentNames.first().component)
    }
}
