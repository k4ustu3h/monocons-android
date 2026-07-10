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

import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class GitHubContributorTest {

    private val json = Json { ignoreUnknownKeys = true }

    @Test
    fun `deserializes from JSON with SerialName mappings`() {
        val jsonString = """
            {
                "id": 12345,
                "login": "example_user",
                "avatar_url": "https://avatars.githubusercontent.com/u/12345",
                "html_url": "https://github.com/example_user",
                "contributions": 42
            }
        """.trimIndent()

        val contributor = json.decodeFromString<GitHubContributor>(jsonString)

        assertEquals(12345L, contributor.id)
        assertEquals("example_user", contributor.login)
        assertEquals("https://avatars.githubusercontent.com/u/12345", contributor.avatarUrl)
        assertEquals("https://github.com/example_user", contributor.htmlUrl)
        assertEquals(42, contributor.contributions)
    }

    @Test
    fun `serializes to JSON with correct field names`() {
        val contributor = GitHubContributor(
            id = 99999,
            login = "test_contributor",
            avatarUrl = "https://example.com/avatar.png",
            htmlUrl = "https://github.com/test_contributor",
            contributions = 100,
        )

        val jsonResult = json.encodeToString(contributor)

        assertTrue(jsonResult.contains("\"id\":99999"))
        assertTrue(jsonResult.contains("\"login\":\"test_contributor\""))
        assertTrue(jsonResult.contains("\"avatar_url\":\"https://example.com/avatar.png\""))
        assertTrue(jsonResult.contains("\"html_url\":\"https://github.com/test_contributor\""))
        assertTrue(jsonResult.contains("\"contributions\":100"))
    }

    @Test
    fun `deserializes ignoring unknown keys`() {
        val jsonString = """
            {
                "id": 1,
                "login": "user",
                "avatar_url": "https://example.com",
                "html_url": "https://github.com/user",
                "contributions": 5,
                "unknown_field": "should be ignored",
                "another_unknown": 12345
            }
        """.trimIndent()

        val contributor = json.decodeFromString<GitHubContributor>(jsonString)

        assertEquals(1L, contributor.id)
        assertEquals("user", contributor.login)
    }

    @Test
    fun `data class equality works correctly`() {
        val contributor1 = GitHubContributor(
            id = 1,
            login = "user",
            avatarUrl = "url",
            htmlUrl = "html",
            contributions = 10,
        )
        val contributor2 = GitHubContributor(
            id = 1,
            login = "user",
            avatarUrl = "url",
            htmlUrl = "html",
            contributions = 10,
        )
        val contributor3 = contributor1.copy(contributions = 20)

        assertEquals(contributor1, contributor2)
        assertNotEquals(contributor1, contributor3)
    }

    @Test
    fun `serialization round trip preserves data`() {
        val original = GitHubContributor(
            id = 54321,
            login = "roundtrip_user",
            avatarUrl = "https://avatars.example.com/54321",
            htmlUrl = "https://github.com/roundtrip_user",
            contributions = 75,
        )

        val jsonString = json.encodeToString(original)
        val deserialized = json.decodeFromString<GitHubContributor>(jsonString)

        assertEquals(original, deserialized)
    }

    @Test
    fun `handles zero contributions`() {
        val jsonString = """
            {
                "id": 1,
                "login": "new_user",
                "avatar_url": "https://example.com/avatar",
                "html_url": "https://github.com/new_user",
                "contributions": 0
            }
        """.trimIndent()

        val contributor = json.decodeFromString<GitHubContributor>(jsonString)

        assertEquals(0, contributor.contributions)
    }
}
