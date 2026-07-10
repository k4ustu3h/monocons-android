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

package k4ustu3h.monocons.data.repository

import k4ustu3h.monocons.data.model.IconInfo
import k4ustu3h.monocons.data.model.IconInfoModel
import k4ustu3h.monocons.data.repository.FakeIconDataSource.Companion.defaultIcons
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import kotlin.time.Duration.Companion.milliseconds
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class NewIconsRepositoryImplTest {

    @Test
    fun `newIconsInfoModel starts empty before background load completes`() = runTest {
        val blockingDataSource = BlockingIconDataSource(defaultIcons)

        val repository = NewIconsRepositoryImpl(
            iconDataSource = blockingDataSource,
        )

        // Ensure getIconInfo started and is blocked so init cannot complete yet.
        assertTrue(blockingDataSource.awaitStarted())
        assertEquals(IconInfoModel(), repository.newIconsInfoModel.value)

        blockingDataSource.unblock()
    }

    @Test
    fun `init loads icons sorted by label and sets icon count`() = runTest {
        val repository = NewIconsRepositoryImpl(
            iconDataSource = FakeIconDataSource(defaultIcons),
        )

        awaitLoaded(repository, expectedSize = defaultIcons.size)

        val model = repository.newIconsInfoModel.value
        assertEquals(
            listOf("Alpha", "Alpha", "My Alpha", "Zalpha"),
            model.iconInfo.map { it.label },
        )
        assertEquals(defaultIcons.size, model.iconCount)
    }

    private suspend fun awaitLoaded(
        repository: NewIconsRepositoryImpl,
        expectedSize: Int,
    ) {
        repeat(200) {
            if (repository.newIconsInfoModel.value.iconInfo.size == expectedSize) {
                return
            }
            delay(10.milliseconds)
        }
        assertTrue(
            "Timed out waiting for repository init",
            repository.newIconsInfoModel.value.iconInfo.size == expectedSize,
        )
    }

    private class BlockingIconDataSource(
        private val icons: List<IconInfo>,
    ) : IconDataSource {

        private val started = CountDownLatch(1)
        private val unblock = CountDownLatch(1)

        override fun getIconInfo(): List<IconInfo> {
            started.countDown()
            unblock.await()
            return icons
        }

        fun awaitStarted(timeoutMs: Long = 2_000L): Boolean = started.await(timeoutMs, TimeUnit.MILLISECONDS)

        fun unblock() {
            unblock.countDown()
        }
    }
}
