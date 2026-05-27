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

package app.lawnchair.lawnicons.data.repository.acknowledgements

import app.lawnchair.lawnicons.data.model.OssLibrary
import app.lawnchair.lawnicons.data.repository.FakeOssLibraryDataSource
import app.lawnchair.lawnicons.data.repository.FakeOssLibraryDataSource.Companion.defaultLibraries
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import kotlin.time.Duration.Companion.milliseconds
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class OssLibraryRepositoryImplTest {

    @Test
    fun `ossLibraries starts empty before data source returns`() = runTest {
        val blockingDataSource = BlockingOssLibraryDataSource(defaultLibraries)
        val repository = OssLibraryRepositoryImpl(
            dataSource = blockingDataSource,
        )

        val collectingJob = launch(start = CoroutineStart.UNDISPATCHED) {
            repository.ossLibraries.first { it.isNotEmpty() }
        }

        assertEquals(emptyList<OssLibrary>(), repository.ossLibraries.value)
        assertTrue(blockingDataSource.awaitStarted())

        blockingDataSource.unblock()
        collectingJob.join()
    }

    @Test
    fun `ossLibraries emits libraries from data source`() = runTest {
        val repository = OssLibraryRepositoryImpl(
            dataSource = FakeOssLibraryDataSource(defaultLibraries),
        )

        awaitLoaded(repository, expectedSize = defaultLibraries.size)

        assertEquals(defaultLibraries, repository.ossLibraries.value)
    }

    @Test
    fun `ossLibraries emits empty list when data source returns empty`() = runTest {
        val repository = OssLibraryRepositoryImpl(
            dataSource = FakeOssLibraryDataSource(emptyList()),
        )

        awaitLoaded(repository, expectedSize = 0, waitForNonEmpty = false)

        // Give the flow time to emit and settle.
        delay(100.milliseconds)
        assertEquals(emptyList<OssLibrary>(), repository.ossLibraries.value)
    }

    @Test
    fun `ossLibraries preserves the order returned by the data source`() = runTest {
        val ordered = defaultLibraries.reversed()
        val repository = OssLibraryRepositoryImpl(
            dataSource = FakeOssLibraryDataSource(ordered),
        )

        awaitLoaded(repository, expectedSize = ordered.size)

        assertEquals(ordered.map { it.name }, repository.ossLibraries.value.map { it.name })
    }

    /**
     * Polls until ossLibraries has the expected size, or times out.
     * When [waitForNonEmpty] is false the poll loop exits immediately (used for empty-list case).
     */
    private suspend fun awaitLoaded(
        repository: OssLibraryRepositoryImpl,
        expectedSize: Int,
        waitForNonEmpty: Boolean = true,
    ) {
        if (!waitForNonEmpty) {
            // Start lazy upstream at least once.
            repository.ossLibraries.first()
            return
        }

        repository.ossLibraries.first { it.size == expectedSize }

        assertTrue(
            "Timed out waiting for ossLibraries to load $expectedSize items",
            repository.ossLibraries.value.size == expectedSize,
        )
    }

    private class BlockingOssLibraryDataSource(
        private val libraries: List<OssLibrary>,
    ) : OssLibraryDataSource {

        private val started = CountDownLatch(1)
        private val unblock = CompletableDeferred<Unit>()

        override suspend fun getOssLibraries(): List<OssLibrary> {
            started.countDown()
            unblock.await()
            return libraries
        }

        fun awaitStarted(timeoutMs: Long = 2_000L): Boolean = started.await(timeoutMs, TimeUnit.MILLISECONDS)

        fun unblock() {
            unblock.complete(Unit)
        }
    }
}
