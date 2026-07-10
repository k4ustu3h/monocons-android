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
import k4ustu3h.monocons.data.model.IconInfo
import k4ustu3h.monocons.data.model.LabelAndComponent
import k4ustu3h.monocons.data.model.SystemIconInfo
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class IconRequestRepositoryFilterTest {

    @Test
    fun `filterUnthemedApps with empty themed list returns all system apps`() {
        val mockDrawable = mock(Drawable::class.java)
        val systemApp = SystemIconInfo(
            drawable = mockDrawable,
            label = "System App",
            component = Component("com.system", "com.system.Activity"),
        )

        val result = filterUnthemedApps(emptyList(), listOf(systemApp))

        assertEquals(1, result.size)
        assertEquals(systemApp, result[0])
    }

    @Test
    fun `filterUnthemedApps with empty system list returns empty list`() {
        val themedIcon = IconInfo(
            drawableName = "themed",
            componentNames = listOf(
                LabelAndComponent("Themed", Component("com.themed", "com.themed.Activity")),
            ),
            drawableId = 1,
        )

        val result = filterUnthemedApps(listOf(themedIcon), emptyList())

        assertTrue(result.isEmpty())
    }

    @Test
    fun `filterUnthemedApps filters out themed apps correctly`() {
        val mockDrawable = mock(Drawable::class.java)
        val themedComponent = Component("com.themed", "com.themed.Activity")
        val themedIcon = IconInfo(
            drawableName = "themed",
            componentNames = listOf(
                LabelAndComponent("Themed App", themedComponent),
            ),
            drawableId = 1,
        )

        val systemApp1 = SystemIconInfo(
            drawable = mockDrawable,
            label = "Themed App",
            component = themedComponent,
        )
        val systemApp2 = SystemIconInfo(
            drawable = mockDrawable,
            label = "Unthemed App",
            component = Component("com.unthemed", "com.unthemed.Activity"),
        )

        val result = filterUnthemedApps(listOf(themedIcon), listOf(systemApp1, systemApp2))

        assertEquals(1, result.size)
        assertEquals(systemApp2, result[0])
    }

    @Test
    fun `filterUnthemedApps handles multiple themed icons`() {
        val mockDrawable = mock(Drawable::class.java)
        val component1 = Component("com.themed1", "com.themed1.Activity")
        val component2 = Component("com.themed2", "com.themed2.Activity")
        val themedIcon1 = IconInfo(
            drawableName = "themed1",
            componentNames = listOf(
                LabelAndComponent("Themed 1", component1),
            ),
            drawableId = 1,
        )
        val themedIcon2 = IconInfo(
            drawableName = "themed2",
            componentNames = listOf(
                LabelAndComponent("Themed 2", component2),
            ),
            drawableId = 2,
        )

        val systemApp1 = SystemIconInfo(
            drawable = mockDrawable,
            label = "Themed 1",
            component = component1,
        )
        val systemApp2 = SystemIconInfo(
            drawable = mockDrawable,
            label = "Themed 2",
            component = component2,
        )
        val systemApp3 = SystemIconInfo(
            drawable = mockDrawable,
            label = "Unthemed",
            component = Component("com.unthemed", "com.unthemed.Activity"),
        )

        val result = filterUnthemedApps(
            listOf(themedIcon1, themedIcon2),
            listOf(systemApp1, systemApp2, systemApp3),
        )

        assertEquals(1, result.size)
        assertEquals(systemApp3, result[0])
    }

    @Test
    fun `filterUnthemedApps handles icon with multiple component names`() {
        val mockDrawable = mock(Drawable::class.java)
        val component1 = Component("com.themed", "com.themed.Activity1")
        val component2 = Component("com.themed", "com.themed.Activity2")
        val multiComponentIcon = IconInfo(
            drawableName = "multi",
            componentNames = listOf(
                LabelAndComponent("Multi", component1),
                LabelAndComponent("Multi", component2),
            ),
            drawableId = 1,
        )

        val systemApp1 = SystemIconInfo(
            drawable = mockDrawable,
            label = "Multi 1",
            component = component1,
        )
        val systemApp2 = SystemIconInfo(
            drawable = mockDrawable,
            label = "Multi 2",
            component = component2,
        )
        val systemApp3 = SystemIconInfo(
            drawable = mockDrawable,
            label = "Unthemed",
            component = Component("com.unthemed", "com.unthemed.Activity"),
        )

        val result = filterUnthemedApps(
            listOf(multiComponentIcon),
            listOf(systemApp1, systemApp2, systemApp3),
        )

        assertEquals(1, result.size)
        assertEquals(systemApp3, result[0])
    }

    @Test
    fun `filterUnthemedApps preserves order of unthemed apps`() {
        val mockDrawable = mock(Drawable::class.java)
        val themedComponent = Component("com.themed", "com.themed.Activity")
        val themedIcon = IconInfo(
            drawableName = "themed",
            componentNames = listOf(
                LabelAndComponent("Themed", themedComponent),
            ),
            drawableId = 1,
        )

        val systemApps = listOf(
            SystemIconInfo(
                drawable = mockDrawable,
                label = "App A",
                component = Component("com.a", "com.a.Activity"),
            ),
            SystemIconInfo(
                drawable = mockDrawable,
                label = "Themed",
                component = themedComponent,
            ),
            SystemIconInfo(
                drawable = mockDrawable,
                label = "App B",
                component = Component("com.b", "com.b.Activity"),
            ),
            SystemIconInfo(
                drawable = mockDrawable,
                label = "App C",
                component = Component("com.c", "com.c.Activity"),
            ),
        )

        val result = filterUnthemedApps(listOf(themedIcon), systemApps)

        assertEquals(3, result.size)
        assertEquals("App A", result[0].label)
        assertEquals("App B", result[1].label)
        assertEquals("App C", result[2].label)
    }

    @Test
    fun `filterUnthemedApps uses flattenToString for component matching`() {
        val mockDrawable = mock(Drawable::class.java)
        val component = Component("com.example", "com.example.Main")
        val themedIcon = IconInfo(
            drawableName = "Example",
            componentNames = listOf(
                LabelAndComponent("Example", component),
            ),
            drawableId = 1,
        )

        val matchingSystemApp = SystemIconInfo(
            drawable = mockDrawable,
            label = "Example",
            component = component,
        )

        val result = filterUnthemedApps(listOf(themedIcon), listOf(matchingSystemApp))

        assertTrue(result.isEmpty())
    }

    @Test
    fun `filterUnthemedApps handles all apps as unthemed`() {
        val mockDrawable = mock(Drawable::class.java)
        val systemApps = listOf(
            SystemIconInfo(
                drawable = mockDrawable,
                label = "App 1",
                component = Component("com.app1", "com.app1.Activity"),
            ),
            SystemIconInfo(
                drawable = mockDrawable,
                label = "App 2",
                component = Component("com.app2", "com.app2.Activity"),
            ),
            SystemIconInfo(
                drawable = mockDrawable,
                label = "App 3",
                component = Component("com.app3", "com.app3.Activity"),
            ),
        )

        val result = filterUnthemedApps(emptyList(), systemApps)

        assertEquals(3, result.size)
    }

    @Test
    fun `filterUnthemedApps handles all apps as themed`() {
        val mockDrawable = mock(Drawable::class.java)
        val component1 = Component("com.app1", "com.app1.Activity")
        val component2 = Component("com.app2", "com.app2.Activity")
        val component3 = Component("com.app3", "com.app3.Activity")

        val themedIcons = listOf(
            IconInfo(
                drawableName = "app1",
                componentNames = listOf(
                    LabelAndComponent("App 1", component1),
                ),
                drawableId = 1,
            ),
            IconInfo(
                drawableName = "app2",
                componentNames = listOf(
                    LabelAndComponent("App 2", component2),
                ),
                drawableId = 2,
            ),
            IconInfo(
                drawableName = "app3",
                componentNames = listOf(
                    LabelAndComponent("App 3", component3),
                ),
                drawableId = 3,
            ),
        )

        val systemApps = listOf(
            SystemIconInfo(
                drawable = mockDrawable,
                label = "App 1",
                component = component1,
            ),
            SystemIconInfo(
                drawable = mockDrawable,
                label = "App 2",
                component = component2,
            ),
            SystemIconInfo(
                drawable = mockDrawable,
                label = "App 3",
                component = component3,
            ),
        )

        val result = filterUnthemedApps(themedIcons, systemApps)

        assertTrue(result.isEmpty())
    }
}
