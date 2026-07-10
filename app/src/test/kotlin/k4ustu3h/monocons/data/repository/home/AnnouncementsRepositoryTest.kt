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

package k4ustu3h.monocons.data.repository.home

import k4ustu3h.monocons.data.api.AnnouncementsAPI
import k4ustu3h.monocons.data.model.Announcement
import k4ustu3h.monocons.data.model.AnnouncementLocation
import k4ustu3h.monocons.data.model.Announcements
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class AnnouncementsRepositoryTest {

    @Test
    fun `getAnnouncements returns announcement list from api`() = runTest {
        val expectedAnnouncements = listOf(
            Announcement(
                title = "New Icons",
                description = "A new icon pack update is available.",
                icon = "https://example.com/icon.png",
                url = "https://example.com/news",
                location = AnnouncementLocation.Home,
            ),
        )
        val repository = AnnouncementsRepository(
            api = FakeAnnouncementsApi(
                response = Announcements(
                    announcements = expectedAnnouncements,
                ),
            ),
        )

        val result = repository.getAnnouncements()

        assertEquals(expectedAnnouncements, result)
    }

    @Test(expected = IllegalStateException::class)
    fun `getAnnouncements propagates api failures`() = runTest {
        val repository = AnnouncementsRepository(
            api = ThrowingAnnouncementsApi,
        )

        repository.getAnnouncements()
    }

    private class FakeAnnouncementsApi(
        private val response: Announcements,
    ) : AnnouncementsAPI {
        override suspend fun getAnnouncements(cacheControl: String): Announcements = response
    }

    private object ThrowingAnnouncementsApi : AnnouncementsAPI {
        override suspend fun getAnnouncements(cacheControl: String): Announcements {
            throw IllegalStateException("failed to fetch announcements")
        }
    }
}
