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

class AnnouncementsTest {

    private val json = Json { ignoreUnknownKeys = true }

    @Test
    fun `AnnouncementLocation enum deserializes Home correctly`() {
        val jsonString = "\"home\""
        val location = json.decodeFromString<AnnouncementLocation>(jsonString)
        assertEquals(AnnouncementLocation.Home, location)
    }

    @Test
    fun `AnnouncementLocation enum deserializes IconRequest correctly`() {
        val jsonString = "\"iconrequest\""
        val location = json.decodeFromString<AnnouncementLocation>(jsonString)
        assertEquals(AnnouncementLocation.IconRequest, location)
    }

    @Test
    fun `AnnouncementLocation enum serializes correctly`() {
        val homeJson = json.encodeToString(AnnouncementLocation.Home)
        val iconRequestJson = json.encodeToString(AnnouncementLocation.IconRequest)

        assertEquals("\"home\"", homeJson)
        assertEquals("\"iconrequest\"", iconRequestJson)
    }

    @Test
    fun `Announcement deserializes correctly`() {
        val jsonString = """
            {
                "title": "New Icons Released",
                "description": "Check out our latest additions!",
                "icon": "https://example.com/icon.png",
                "url": "https://example.com/announcement",
                "location": "home"
            }
        """.trimIndent()

        val announcement = json.decodeFromString<Announcement>(jsonString)

        assertEquals("New Icons Released", announcement.title)
        assertEquals("Check out our latest additions!", announcement.description)
        assertEquals("https://example.com/icon.png", announcement.icon)
        assertEquals("https://example.com/announcement", announcement.url)
        assertEquals(AnnouncementLocation.Home, announcement.location)
    }

    @Test
    fun `Announcements deserializes with multiple announcements`() {
        val jsonString = """
            {
                "version": 2,
                "announcements": [
                    {
                        "title": "First Announcement",
                        "description": "Description 1",
                        "icon": "icon1",
                        "url": "url1",
                        "location": "home"
                    },
                    {
                        "title": "Second Announcement",
                        "description": "Description 2",
                        "icon": "icon2",
                        "url": "url2",
                        "location": "iconrequest"
                    }
                ]
            }
        """.trimIndent()

        val announcements = json.decodeFromString<Announcements>(jsonString)

        assertEquals(2, announcements.version)
        assertEquals(2, announcements.announcements.size)
        assertEquals("First Announcement", announcements.announcements[0].title)
        assertEquals(AnnouncementLocation.Home, announcements.announcements[0].location)
        assertEquals("Second Announcement", announcements.announcements[1].title)
        assertEquals(AnnouncementLocation.IconRequest, announcements.announcements[1].location)
    }

    @Test
    fun `Announcements default version is 2`() {
        val announcements = Announcements(announcements = emptyList())
        assertEquals(2, announcements.version)
    }

    @Test
    fun `Announcements handles empty announcements list`() {
        val jsonString = """
            {
                "version": 2,
                "announcements": []
            }
        """.trimIndent()

        val announcements = json.decodeFromString<Announcements>(jsonString)

        assertEquals(2, announcements.version)
        assertTrue(announcements.announcements.isEmpty())
    }

    @Test
    fun `Announcements serialization round trip preserves data`() {
        val original = Announcements(
            version = 3,
            announcements = listOf(
                Announcement(
                    title = "Test",
                    description = "Test Description",
                    icon = "test_icon",
                    url = "https://test.com",
                    location = AnnouncementLocation.Home,
                ),
            ),
        )

        val jsonString = json.encodeToString(original)
        val deserialized = json.decodeFromString<Announcements>(jsonString)

        assertEquals(original, deserialized)
    }

    @Test
    fun `Announcement data class equality works correctly`() {
        val announcement1 = Announcement(
            title = "Title",
            description = "Desc",
            icon = "icon",
            url = "url",
            location = AnnouncementLocation.Home,
        )
        val announcement2 = Announcement(
            title = "Title",
            description = "Desc",
            icon = "icon",
            url = "url",
            location = AnnouncementLocation.Home,
        )
        val announcement3 = announcement1.copy(title = "Different Title")

        assertEquals(announcement1, announcement2)
        assertNotEquals(announcement1, announcement3)
    }

    @Test
    fun `Announcements ignores unknown keys in JSON`() {
        val jsonString = """
            {
                "version": 2,
                "announcements": [],
                "unknown_field": "should be ignored"
            }
        """.trimIndent()

        val announcements = json.decodeFromString<Announcements>(jsonString)

        assertEquals(2, announcements.version)
    }
}
