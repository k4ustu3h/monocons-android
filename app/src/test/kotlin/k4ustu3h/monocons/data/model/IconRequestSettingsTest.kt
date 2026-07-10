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
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class IconRequestSettingsTest {

    private val json = Json { ignoreUnknownKeys = true }

    @Test
    fun `deserializes enabled true from JSON`() {
        val jsonString = """{"enabled": true}"""
        val settings = json.decodeFromString<IconRequestSettings>(jsonString)
        assertTrue(settings.enabled)
    }

    @Test
    fun `deserializes enabled false from JSON`() {
        val jsonString = """{"enabled": false}"""
        val settings = json.decodeFromString<IconRequestSettings>(jsonString)
        assertFalse(settings.enabled)
    }

    @Test
    fun `serializes to JSON correctly`() {
        val settingsEnabled = IconRequestSettings(enabled = true)
        val settingsDisabled = IconRequestSettings(enabled = false)

        val jsonEnabled = json.encodeToString(settingsEnabled)
        val jsonDisabled = json.encodeToString(settingsDisabled)

        assertTrue(jsonEnabled.contains("\"enabled\":true"))
        assertTrue(jsonDisabled.contains("\"enabled\":false"))
    }

    @Test
    fun `serialization round trip preserves data`() {
        val original = IconRequestSettings(enabled = true)

        val jsonString = json.encodeToString(original)
        val deserialized = json.decodeFromString<IconRequestSettings>(jsonString)

        assertEquals(original, deserialized)
    }

    @Test
    fun `data class equality works correctly`() {
        val settings1 = IconRequestSettings(enabled = true)
        val settings2 = IconRequestSettings(enabled = true)
        val settings3 = IconRequestSettings(enabled = false)

        assertEquals(settings1, settings2)
        assertNotEquals(settings1, settings3)
    }

    @Test
    fun `ignores unknown keys in JSON`() {
        val jsonString = """
            {
                "enabled": true,
                "unknown_field": "should be ignored",
                "another_unknown": 12345
            }
        """.trimIndent()

        val settings = json.decodeFromString<IconRequestSettings>(jsonString)

        assertTrue(settings.enabled)
    }

    @Test
    fun `copy function works correctly`() {
        val original = IconRequestSettings(enabled = true)
        val copied = original.copy(enabled = false)

        assertTrue(original.enabled)
        assertFalse(copied.enabled)
    }
}
