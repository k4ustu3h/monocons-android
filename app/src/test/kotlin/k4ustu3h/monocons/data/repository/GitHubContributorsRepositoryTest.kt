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

import k4ustu3h.monocons.data.api.GitHubContributorsAPI
import k4ustu3h.monocons.data.model.GitHubContributor
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class GitHubContributorsRepositoryTest {

    private fun contributor(
        id: Long,
        login: String = "user_$id",
        contributions: Int = 1,
    ) = GitHubContributor(
        id = id,
        login = login,
        avatarUrl = "https://avatars.githubusercontent.com/u/$id",
        htmlUrl = "https://github.com/$login",
        contributions = contributions,
    )

    // Ids that simulate coreContributorIds in production without importing Compose/R sources.
    private val coreIds = listOf(10L, 20L, 30L)

    private fun repository(contributors: List<GitHubContributor>) = GitHubContributorsRepository(
        api = FakeGitHubContributorsApi(contributors),
        contributorIds = coreIds,
    )

    @Test
    fun `getTopContributors filters out core contributor ids`() = runTest {
        val contributors = listOf(
            contributor(id = 10L, contributions = 100), // core, should be excluded
            contributor(id = 20L, contributions = 90), // core, should be excluded
            contributor(id = 99L, contributions = 50), // community, should be included
        )

        val result = repository(contributors).getTopContributors()

        assertFalse("Core ids must not appear in result", result.any { it.id in coreIds })
        assertEquals(1, result.size)
        assertEquals(99L, result.first().id)
    }

    @Test
    fun `getTopContributors sorts remaining contributors descending by contributions`() = runTest {
        val contributors = listOf(
            contributor(id = 1L, contributions = 5),
            contributor(id = 2L, contributions = 50),
            contributor(id = 3L, contributions = 25),
        )

        val result = repository(contributors).getTopContributors()

        assertEquals(listOf(50, 25, 5), result.map { it.contributions })
    }

    @Test
    fun `getTopContributors returns empty list when all contributors are core`() = runTest {
        val contributors =
            coreIds.mapIndexed { i, id -> contributor(id = id, contributions = i + 1) }

        val result = repository(contributors).getTopContributors()

        assertTrue(result.isEmpty())
    }

    @Test
    fun `getTopContributors returns empty list when api returns empty`() = runTest {
        val result = repository(emptyList()).getTopContributors()

        assertTrue(result.isEmpty())
    }

    @Test(expected = IllegalStateException::class)
    fun `getTopContributors propagates api failures`() = runTest {
        val throwingRepo = GitHubContributorsRepository(
            api = ThrowingGitHubContributorsApi,
            contributorIds = coreIds,
        )

        throwingRepo.getTopContributors()
    }

    @Test
    fun `getTopContributors includes contributors with equal contribution counts in stable order`() = runTest {
        val contributors = listOf(
            contributor(id = 1L, login = "alice", contributions = 10),
            contributor(id = 2L, login = "bob", contributions = 10),
            contributor(id = 3L, login = "carol", contributions = 10),
        )

        val result = repository(contributors).getTopContributors()

        assertEquals(3, result.size)
        assertEquals(listOf(10, 10, 10), result.map { it.contributions })
    }

    private class FakeGitHubContributorsApi(
        private val contributors: List<GitHubContributor>,
    ) : GitHubContributorsAPI {
        override suspend fun getContributors(): List<GitHubContributor> = contributors
    }

    private object ThrowingGitHubContributorsApi : GitHubContributorsAPI {
        override suspend fun getContributors(): List<GitHubContributor> {
            throw IllegalStateException("network error")
        }
    }
}
