package app.lawnchair.lawnicons.data.model

import app.lawnchair.lawnicons.ui.theme.icon.Back
import app.lawnchair.lawnicons.ui.theme.icon.Check
import app.lawnchair.lawnicons.ui.theme.icon.Close
import app.lawnchair.lawnicons.ui.theme.icon.LawnIcons
import app.lawnchair.lawnicons.ui.theme.icon.Search
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class IconInfoTest {

    @Test
    fun `fallbackImage returns Check for drawableId 1`() {
        val iconInfo = IconInfo(
            drawableName = "test_check",
            componentNames = emptyList(),
            drawableId = 1,
        )
        val result = iconInfo.fallbackImage
        assertEquals(LawnIcons.Check, result)
    }

    @Test
    fun `fallbackImage returns Close for drawableId 2`() {
        val iconInfo = IconInfo(
            drawableName = "test_close",
            componentNames = emptyList(),
            drawableId = 2,
        )
        val result = iconInfo.fallbackImage
        assertEquals(LawnIcons.Close, result)
    }

    @Test
    fun `fallbackImage returns Search for drawableId 3`() {
        val iconInfo = IconInfo(
            drawableName = "test_search",
            componentNames = emptyList(),
            drawableId = 3,
        )
        val result = iconInfo.fallbackImage
        assertEquals(LawnIcons.Search, result)
    }

    @Test
    fun `fallbackImage returns Back for any other drawableId`() {
        val iconInfo = IconInfo(
            drawableName = "test_other",
            componentNames = emptyList(),
            drawableId = 999,
        )
        val result = iconInfo.fallbackImage
        assertEquals(LawnIcons.Back, result)
    }

    @Test
    fun `label property returns first component label`() {
        val iconInfo = IconInfo(
            drawableName = "test_icon",
            componentNames = listOf(
                LabelAndComponent("First App", "com.first/Activity"),
                LabelAndComponent("Second App", "com.second/Activity"),
            ),
            drawableId = 1,
        )
        assertEquals("First App", iconInfo.label)
    }

    @Test
    fun `label property returns empty string when no components exist`() {
        val iconInfo = IconInfo(
            drawableName = "test_icon",
            componentNames = emptyList(),
            drawableId = 1,
        )
        assertEquals("", iconInfo.label)
    }

    @Test
    fun `mergeByDrawableName merges component names correctly for same drawable names`() {
        val icon1a = IconInfo(
            drawableName = "icon_a",
            componentNames = listOf(LabelAndComponent("AppA1", "app.compA1/cls.name")),
            drawableId = 1,
        )
        val icon1b = IconInfo(
            drawableName = "icon_a",
            componentNames = listOf(LabelAndComponent("AppA2", "app.compA2/cls.name")),
            drawableId = 1,
        )
        val icon2 = IconInfo(
            drawableName = "icon_b",
            componentNames = listOf(LabelAndComponent("AppB1", "app.compB1/cls.name")),
            drawableId = 2,
        )
        val listToMerge = listOf(icon1a, icon1b, icon2)
        val mergedList = listToMerge.mergeByDrawableName()
        assertEquals(2, mergedList.size)
        val mergedIconA = mergedList.find { it.drawableName == "icon_a" }
        assertNotNull(mergedIconA)
        assertEquals(
            listOf(
                LabelAndComponent("AppA1", "app.compA1/cls.name"),
                LabelAndComponent("AppA2", "app.compA2/cls.name"),
            ),
            mergedIconA?.componentNames,
        )
        assertEquals(1, mergedIconA?.drawableId)
        val mergedIconB = mergedList.find { it.drawableName == "icon_b" }
        assertNotNull(mergedIconB)
        assertEquals(
            listOf(LabelAndComponent("AppB1", "app.compB1/cls.name")),
            mergedIconB?.componentNames,
        )
        assertEquals(2, mergedIconB?.drawableId)
    }

    @Test
    fun `splitByComponentName splits icons with multiple components into single-component icons`() {
        val icon1 = IconInfo(
            drawableName = "icon_multi",
            componentNames = listOf(
                LabelAndComponent("AppM1", "comp.M1/.activity"),
                LabelAndComponent("AppM2", "comp.M2/.activity"),
            ),
            drawableId = 1,
        )
        val icon2 = IconInfo(
            drawableName = "icon_single",
            componentNames = listOf(LabelAndComponent("AppS1", "comp.S1/.activity")),
            drawableId = 2,
        )
        val listToSplit = listOf(icon1, icon2)
        val splitList = listToSplit.splitByComponentName()
        assertEquals(3, splitList.size)
        val expectedSplitIcons = listOf(
            icon1.copy(componentNames = listOf(LabelAndComponent("AppM1", "comp.M1/.activity"))),
            icon1.copy(componentNames = listOf(LabelAndComponent("AppM2", "comp.M2/.activity"))),
            icon2,
        )
        assertEquals(
            expectedSplitIcons.sortedBy { it.drawableName + it.componentNames.first().component.className },
            splitList.sortedBy { it.drawableName + it.componentNames.first().component.className },
        )
    }

    @Test
    fun `mergeByDrawableName returns empty list when input is empty`() {
        val emptyList = emptyList<IconInfo>()
        val result = emptyList.mergeByDrawableName()
        assertTrue(result.isEmpty())
    }

    @Test
    fun `splitByComponentName returns empty list when input is empty`() {
        val emptyList = emptyList<IconInfo>()
        val result = emptyList.splitByComponentName()
        assertTrue(result.isEmpty())
    }

    @Test
    fun `mergeByDrawableName preserves drawableId from first icon`() {
        val icon1 = IconInfo(
            drawableName = "same_icon",
            componentNames = listOf(LabelAndComponent("App1", "com.app1/Activity")),
            drawableId = 100,
        )
        val icon2 = IconInfo(
            drawableName = "same_icon",
            componentNames = listOf(LabelAndComponent("App2", "com.app2/Activity")),
            drawableId = 200,
        )
        val merged = listOf(icon1, icon2).mergeByDrawableName()
        assertEquals(1, merged.size)
        assertEquals(100, merged.first().drawableId)
    }

    @Test
    fun `data class equality works correctly`() {
        val iconInfo1 = IconInfo(
            drawableName = "icon",
            componentNames = listOf(LabelAndComponent("App", "com.app/Activity")),
            drawableId = 1,
        )
        val iconInfo2 = IconInfo(
            drawableName = "icon",
            componentNames = listOf(LabelAndComponent("App", "com.app/Activity")),
            drawableId = 1,
        )
        val iconInfo3 = iconInfo1.copy(drawableName = "different_icon")
        assertEquals(iconInfo1, iconInfo2)
        assertNotEquals(iconInfo1, iconInfo3)
    }

    @Test
    fun `fallbackImage returns Back for zero drawableId`() {
        val iconInfo = IconInfo(
            drawableName = "test_zero",
            componentNames = emptyList(),
            drawableId = 0,
        )
        assertEquals(LawnIcons.Back, iconInfo.fallbackImage)
    }

    @Test
    fun `fallbackImage returns Back for negative drawableId`() {
        val iconInfo = IconInfo(
            drawableName = "test_negative",
            componentNames = emptyList(),
            drawableId = -1,
        )
        assertEquals(LawnIcons.Back, iconInfo.fallbackImage)
    }

    @Test
    fun `splitByComponentName preserves all icon properties`() {
        val icon = IconInfo(
            drawableName = "multi_icon",
            componentNames = listOf(
                LabelAndComponent("App1", "comp.M1/.activity"),
                LabelAndComponent("App2", "comp.M1/.activity"),
            ),
            drawableId = 42,
        )
        val split = listOf(icon).splitByComponentName()
        assertEquals(2, split.size)
        split.forEach { splitIcon ->
            assertEquals("multi_icon", splitIcon.drawableName)
            assertEquals(42, splitIcon.drawableId)
            assertEquals(1, splitIcon.componentNames.size)
        }
    }

    @Test
    fun `mergeByDrawableName with identical labels preserves all`() {
        val icon1 = IconInfo(
            drawableName = "icon",
            componentNames = listOf(LabelAndComponent("Same Label", "com.app1/Activity")),
            drawableId = 1,
        )
        val icon2 = IconInfo(
            drawableName = "icon",
            componentNames = listOf(LabelAndComponent("Same Label", "com.app2/Activity")),
            drawableId = 1,
        )
        val merged = listOf(icon1, icon2).mergeByDrawableName()
        assertEquals(1, merged.size)
        assertEquals(2, merged.first().componentNames.size)
        assertEquals("Same Label", merged.first().componentNames[0].label)
        assertEquals("Same Label", merged.first().componentNames[1].label)
    }

    @Test
    fun `mergeByDrawableName merges icons that already have multiple componentNames`() {
        val icon1 = IconInfo(
            drawableName = "shared_icon",
            componentNames = listOf(
                LabelAndComponent("App A1", "com.a1/Activity"),
                LabelAndComponent("App A2", "com.a2/Activity"),
            ),
            drawableId = 5,
        )
        val icon2 = IconInfo(
            drawableName = "shared_icon",
            componentNames = listOf(
                LabelAndComponent("App B1", "com.b1/Activity"),
                LabelAndComponent("App B2", "com.b2/Activity"),
            ),
            drawableId = 5,
        )
        val merged = listOf(icon1, icon2).mergeByDrawableName()
        assertEquals(1, merged.size)
        assertEquals(4, merged.first().componentNames.size)
        assertEquals(5, merged.first().drawableId)

        val labels = merged.first().componentNames.map { it.label }
        assertTrue(labels.contains("App A1"))
        assertTrue(labels.contains("App A2"))
        assertTrue(labels.contains("App B1"))
        assertTrue(labels.contains("App B2"))
    }
}
