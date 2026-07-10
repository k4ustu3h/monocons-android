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

class OssLibraryTest {

    private val json = Json { ignoreUnknownKeys = true }

    @Test
    fun `is correctly deserialized from JSON`() {
        val jsonString = """
            {
                "groupId": "androidx.compose.ui",
                "artifactId": "ui",
                "version": "1.7.0",
                "name": "Compose UI",
                "scm": {
                    "url": "https://github.com/androidx/androidx"
                },
                "spdxLicenses": [
                    { "name": "Apache-2.0" }
                ]
            }
        """.trimIndent()

        val library = json.decodeFromString<OssLibrary>(jsonString)

        assertEquals("androidx.compose.ui", library.groupId)
        assertEquals("ui", library.artifactId)
        assertEquals("1.7.0", library.version)
        assertEquals("Compose UI", library.name)
        assertEquals("https://github.com/androidx/androidx", library.scm.url)
        assertEquals(1, library.spdxLicenses.size)
        assertEquals("Apache-2.0", library.spdxLicenses[0].name)
    }

    @Test
    fun `is correctly serialized to JSON`() {
        val library = OssLibrary(
            groupId = "com.example",
            artifactId = "test-lib",
            version = "1.0.0",
            name = "Test Library",
            scm = OssLibrary.Scm(url = "https://example.com"),
            spdxLicenses = listOf(OssLibrary.License(name = "MIT")),
        )

        val jsonResult = json.encodeToString(library)

        assertTrue(jsonResult.contains("\"groupId\":\"com.example\""))
        assertTrue(jsonResult.contains("\"name\":\"Test Library\""))
        assertTrue(jsonResult.contains("\"url\":\"https://example.com\""))
    }

    @Test
    fun `serialization round trip preserves all fields`() {
        val original = OssLibrary(
            groupId = "org.jetbrains.kotlin",
            artifactId = "kotlin-stdlib",
            version = "2.0.0",
            name = "Kotlin Standard Library",
            scm = OssLibrary.Scm(url = "https://github.com/JetBrains/kotlin"),
            spdxLicenses = listOf(
                OssLibrary.License(name = "Apache-2.0"),
                OssLibrary.License(name = "MIT"),
            ),
        )

        val jsonString = json.encodeToString(original)
        val deserialized = json.decodeFromString<OssLibrary>(jsonString)

        assertEquals(original, deserialized)
    }

    @Test
    fun `data class equality works as expected`() {
        val lib1 = OssLibrary("g", "a", "v", "n", OssLibrary.Scm("u"), emptyList())
        val lib2 = OssLibrary("g", "a", "v", "n", OssLibrary.Scm("u"), emptyList())
        val lib3 = lib1.copy(version = "2.0.0")

        assertEquals(lib1, lib2)
        assertNotEquals(lib1, lib3)
    }

    @Test
    fun `handles multiple spdx licenses`() {
        val jsonString = """
            {
                "groupId": "com.multi",
                "artifactId": "multi-license",
                "version": "1.0.0",
                "name": "Multi License Lib",
                "scm": { "url": "https://example.com" },
                "spdxLicenses": [
                    { "name": "Apache-2.0" },
                    { "name": "MIT" },
                    { "name": "BSD-2-Clause" }
                ]
            }
        """.trimIndent()

        val library = json.decodeFromString<OssLibrary>(jsonString)

        assertEquals(3, library.spdxLicenses.size)
        assertEquals("Apache-2.0", library.spdxLicenses[0].name)
        assertEquals("MIT", library.spdxLicenses[1].name)
        assertEquals("BSD-2-Clause", library.spdxLicenses[2].name)
    }

    @Test
    fun `handles empty spdx licenses list`() {
        val jsonString = """
            {
                "groupId": "com.example",
                "artifactId": "no-license",
                "version": "1.0.0",
                "name": "No License Lib",
                "scm": { "url": "https://example.com" },
                "spdxLicenses": []
            }
        """.trimIndent()

        val library = json.decodeFromString<OssLibrary>(jsonString)

        assertTrue(library.spdxLicenses.isEmpty())
    }

    @Test
    fun `ignores unknown keys in JSON`() {
        val jsonString = """
            {
                "groupId": "com.example",
                "artifactId": "test",
                "version": "1.0.0",
                "name": "Test",
                "scm": { "url": "https://example.com" },
                "spdxLicenses": [],
                "unknown_field": "should be ignored"
            }
        """.trimIndent()

        val library = json.decodeFromString<OssLibrary>(jsonString)

        assertEquals("com.example", library.groupId)
    }

    @Test
    fun `Scm data class equality works correctly`() {
        val scm1 = OssLibrary.Scm("https://example.com")
        val scm2 = OssLibrary.Scm("https://example.com")
        val scm3 = OssLibrary.Scm("https://other.com")

        assertEquals(scm1, scm2)
        assertNotEquals(scm1, scm3)
    }

    @Test
    fun `License data class equality works correctly`() {
        val license1 = OssLibrary.License("Apache-2.0")
        val license2 = OssLibrary.License("Apache-2.0")
        val license3 = OssLibrary.License("MIT")

        assertEquals(license1, license2)
        assertNotEquals(license1, license3)
    }

    @Test
    fun `copy function updates the correct field`() {
        val original = OssLibrary("g", "a", "1.0.0", "n", OssLibrary.Scm("u"), emptyList())
        val updated = original.copy(version = "2.0.0")

        assertEquals("1.0.0", original.version)
        assertEquals("2.0.0", updated.version)
        assertEquals(original.groupId, updated.groupId)
        assertEquals(original.artifactId, updated.artifactId)
        assertEquals(original.name, updated.name)
        assertEquals(original.scm, updated.scm)
    }
}
