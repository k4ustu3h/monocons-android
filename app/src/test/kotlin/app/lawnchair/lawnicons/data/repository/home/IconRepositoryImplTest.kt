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

package app.lawnchair.lawnicons.data.repository.home

import app.lawnchair.lawnicons.data.model.IconInfo
import app.lawnchair.lawnicons.data.model.LabelAndComponent
import app.lawnchair.lawnicons.data.model.SearchMode
import kotlin.time.Duration.Companion.milliseconds
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class IconRepositoryImplTest {

    @Test
    fun `init loads sorted icon list and unique label count`() = runTest {
        val repository = IconRepositoryImpl(
            iconDataSource = FakeIconDataSource(iconFixtures),
        )

        awaitLoaded(repository, expectedSize = iconFixtures.size)

        val model = repository.iconInfoModel.value
        assertEquals(
            listOf("Alpha", "Alpha", "My Alpha", "Zalpha"),
            model.iconInfo.map { it.label },
        )
        assertEquals(3, model.iconCount)
        assertEquals(model, repository.searchedIconInfoModel.value)
    }

    @Test
    fun `search in label mode prioritizes word-start then lower index`() = runTest {
        val repository = IconRepositoryImpl(
            iconDataSource = FakeIconDataSource(iconFixtures),
        )
        awaitLoaded(repository, expectedSize = iconFixtures.size)

        repository.search(SearchMode.LABEL, "alpha")

        val labels = repository.searchedIconInfoModel.value.iconInfo.map { it.label }
        assertEquals(listOf("Alpha", "Alpha", "My Alpha", "Zalpha"), labels)
    }

    @Test
    fun `search in component mode finds matching component strings`() = runTest {
        val repository = IconRepositoryImpl(
            iconDataSource = FakeIconDataSource(iconFixtures),
        )
        awaitLoaded(repository, expectedSize = iconFixtures.size)

        repository.search(SearchMode.COMPONENT, "settings")

        val drawables = repository.searchedIconInfoModel.value.iconInfo.map { it.drawableName }
        assertEquals(listOf("alpha_settings"), drawables)
    }

    @Test
    fun `search in drawable mode filters by drawable name`() = runTest {
        val repository = IconRepositoryImpl(
            iconDataSource = FakeIconDataSource(iconFixtures),
        )
        awaitLoaded(repository, expectedSize = iconFixtures.size)

        repository.search(SearchMode.DRAWABLE, "alpha")

        val drawables = repository.searchedIconInfoModel.value.iconInfo.map { it.drawableName }
        assertEquals(
            listOf("alpha_mail", "alpha_settings", "zalpha_notes", "my_alpha_tools"),
            drawables,
        )
    }

    @Test
    fun `clearSearch restores full list after filtering`() = runTest {
        val repository = IconRepositoryImpl(
            iconDataSource = FakeIconDataSource(iconFixtures),
        )
        awaitLoaded(repository, expectedSize = iconFixtures.size)

        repository.search(SearchMode.COMPONENT, "settings")
        assertEquals(1, repository.searchedIconInfoModel.value.iconInfo.size)

        repository.clearSearch()

        assertEquals(
            repository.iconInfoModel.value,
            repository.searchedIconInfoModel.value,
        )
    }

    @Test
    fun `search with empty query returns all icons`() = runTest {
        val repository = IconRepositoryImpl(
            iconDataSource = FakeIconDataSource(iconFixtures),
        )
        awaitLoaded(repository, expectedSize = iconFixtures.size)

        repository.search(SearchMode.LABEL, "")

        assertEquals(repository.iconInfoModel.value, repository.searchedIconInfoModel.value)
    }

    private suspend fun awaitLoaded(
        repository: IconRepositoryImpl,
        expectedSize: Int,
    ) {
        repeat(200) {
            if (repository.iconInfoModel.value.iconInfo.size == expectedSize) {
                return
            }
            delay(10.milliseconds)
        }
        assertTrue(
            "Timed out waiting for repository init",
            repository.iconInfoModel.value.iconInfo.size == expectedSize,
        )
    }

    private class FakeIconDataSource(
        private val icons: List<IconInfo>,
    ) : IconDataSource {
        override fun getIconInfo(): List<IconInfo> = icons
    }

    private companion object {
        val iconFixtures = listOf(
            IconInfo(
                drawableName = "zalpha_notes",
                componentNames = listOf(
                    LabelAndComponent("Zalpha", "com.example.notes/.MainActivity"),
                ),
                drawableId = 10,
            ),
            IconInfo(
                drawableName = "my_alpha_tools",
                componentNames = listOf(
                    LabelAndComponent("My Alpha", "com.example.tools/.MainActivity"),
                ),
                drawableId = 11,
            ),
            IconInfo(
                drawableName = "alpha_mail",
                componentNames = listOf(
                    LabelAndComponent("Alpha", "com.example.mail/.MainActivity"),
                ),
                drawableId = 12,
            ),
            IconInfo(
                drawableName = "alpha_settings",
                componentNames = listOf(
                    LabelAndComponent("Alpha", "com.example.settings/.MainActivity"),
                ),
                drawableId = 13,
            ),
        )
    }
}

