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
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SearchInfoTest {

    @Test
    fun `SearchInfo constructor initializes all fields`() {
        val iconInfo = IconInfo(
            drawableName = "test_icon",
            componentNames = listOf(LabelAndComponent("Test App", "com.test/Activity")),
            drawableId = 1,
        )

        val searchInfo = SearchInfo(
            iconInfo = iconInfo,
            indexOfMatch = 5,
            matchAtWordStart = true,
        )

        assertEquals(iconInfo, searchInfo.iconInfo)
        assertEquals(5, searchInfo.indexOfMatch)
        assertTrue(searchInfo.matchAtWordStart)
    }

    @Test
    fun `SearchInfo matchAtWordStart can be false`() {
        val iconInfo = IconInfo(
            drawableName = "icon",
            componentNames = emptyList(),
            drawableId = 1,
        )

        val searchInfo = SearchInfo(
            iconInfo = iconInfo,
            indexOfMatch = 10,
            matchAtWordStart = false,
        )

        assertFalse(searchInfo.matchAtWordStart)
    }

    @Test
    fun `SearchInfo with zero indexOfMatch`() {
        val iconInfo = IconInfo(
            drawableName = "icon",
            componentNames = emptyList(),
            drawableId = 1,
        )

        val searchInfo = SearchInfo(
            iconInfo = iconInfo,
            indexOfMatch = 0,
            matchAtWordStart = true,
        )

        assertEquals(0, searchInfo.indexOfMatch)
    }

    @Test
    fun `SearchMode enum has all expected values`() {
        val modes = SearchMode.entries

        assertEquals(3, modes.size)
        assertTrue(modes.contains(SearchMode.LABEL))
        assertTrue(modes.contains(SearchMode.COMPONENT))
        assertTrue(modes.contains(SearchMode.DRAWABLE))
    }

    @Test
    fun `SearchMode LABEL is accessible`() {
        val mode = SearchMode.LABEL
        assertEquals("LABEL", mode.name)
    }

    @Test
    fun `SearchMode COMPONENT is accessible`() {
        val mode = SearchMode.COMPONENT
        assertEquals("COMPONENT", mode.name)
    }

    @Test
    fun `SearchMode DRAWABLE is accessible`() {
        val mode = SearchMode.DRAWABLE
        assertEquals("DRAWABLE", mode.name)
    }

    @Test
    fun `SearchInfo data class equality works correctly`() {
        val iconInfo = IconInfo(
            drawableName = "icon",
            componentNames = emptyList(),
            drawableId = 1,
        )

        val searchInfo1 = SearchInfo(
            iconInfo = iconInfo,
            indexOfMatch = 5,
            matchAtWordStart = true,
        )
        val searchInfo2 = SearchInfo(
            iconInfo = iconInfo,
            indexOfMatch = 5,
            matchAtWordStart = true,
        )
        val searchInfo3 = searchInfo1.copy(indexOfMatch = 10)

        assertEquals(searchInfo1, searchInfo2)
        assertNotEquals(searchInfo1, searchInfo3)
    }

    @Test
    fun `SearchInfo can be sorted by indexOfMatch`() {
        val iconInfo = IconInfo(
            drawableName = "icon",
            componentNames = emptyList(),
            drawableId = 1,
        )

        val searchResults = listOf(
            SearchInfo(iconInfo, indexOfMatch = 10, matchAtWordStart = false),
            SearchInfo(iconInfo, indexOfMatch = 0, matchAtWordStart = true),
            SearchInfo(iconInfo, indexOfMatch = 5, matchAtWordStart = true),
        )

        val sorted = searchResults.sortedBy { it.indexOfMatch }

        assertEquals(0, sorted[0].indexOfMatch)
        assertEquals(5, sorted[1].indexOfMatch)
        assertEquals(10, sorted[2].indexOfMatch)
    }
}
