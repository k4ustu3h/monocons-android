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

import android.app.Application
import app.lawnchair.lawnicons.data.api.IconRequestSettingsAPI
import app.lawnchair.lawnicons.data.model.IconRequestSettings
import app.lawnchair.lawnicons.data.repository.FakeIconDataSource
import app.lawnchair.lawnicons.data.repository.FakeIconDataSource.Companion.defaultIcons
import app.lawnchair.lawnicons.data.repository.InMemoryPreferenceStore
import app.lawnchair.lawnicons.data.repository.PreferenceManager
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.Mockito.mock

class IconRequestRepositoryImplTest {

    @Test
    fun `createIconRequestZip returns null when iconRequestList is empty`() = runTest {
        val context = mock(Application::class.java)
        val result = IconRequestBundler.createIconRequestZip(context, emptyList())
        assertNull(result)
    }

    @Test
    fun `refresh sets isEnabled true when force preference is enabled and api is disabled`() =
        runTest {
            val repository = createRepository(
                forceEnable = true,
                api = StaticIconRequestSettingsApi(enabled = false),
            )

            repository.refresh()

            assertTrue(repository.isEnabled.value)
        }

    @Test
    fun `refresh sets isEnabled true when api fails and force preference is enabled`() = runTest {
        val repository = createRepository(
            forceEnable = true,
            api = ThrowingIconRequestSettingsApi(),
        )

        repository.refresh()

        assertTrue(repository.isEnabled.value)
    }

    @Test
    fun `refresh sets isEnabled false when api is disabled and force preference is disabled`() =
        runTest {
            val repository = createRepository(
                forceEnable = false,
                api = StaticIconRequestSettingsApi(enabled = false),
            )

            repository.refresh()

            assertFalse(repository.isEnabled.value)
        }

    @Test
    fun `refresh sets empty iconRequestList when icon sources fail in JVM unit test environment`() =
        runTest {
            val repository = createRepository(
                forceEnable = false,
                api = StaticIconRequestSettingsApi(enabled = false),
            )

            repository.refresh()

            val model = repository.iconRequestList.value
            assertEquals(0, model?.iconCount)
            assertTrue(model?.list?.isEmpty() == true)
        }

    private fun createRepository(
        forceEnable: Boolean,
        api: IconRequestSettingsAPI,
    ): IconRequestRepositoryImpl {
        val preferenceManager = PreferenceManager(InMemoryPreferenceStore()).apply {
            forceEnableIconRequest.set(forceEnable)
        }

        return IconRequestRepositoryImpl(
            iconDataSource = FakeIconDataSource(defaultIcons),
            application = mock(Application::class.java),
            api = api,
            preferenceManager = preferenceManager,
        )
    }

    private class StaticIconRequestSettingsApi(
        private val enabled: Boolean,
    ) : IconRequestSettingsAPI {
        override suspend fun getIconRequestSettings() = IconRequestSettings(enabled = enabled)
    }

    private class ThrowingIconRequestSettingsApi : IconRequestSettingsAPI {
        override suspend fun getIconRequestSettings(): IconRequestSettings {
            throw IllegalStateException("failed to load settings")
        }
    }
}
