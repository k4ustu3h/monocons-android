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
import k4ustu3h.monocons.data.model.LabelAndComponent
import k4ustu3h.monocons.data.model.OssLibrary
import k4ustu3h.monocons.data.repository.acknowledgements.OssLibraryDataSource

class FakeIconDataSource(
    private val icons: List<IconInfo> = defaultIcons,
) : IconDataSource {
    override fun getIconInfo(): List<IconInfo> = icons

    companion object {
        val defaultIcons = listOf(
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

class FakeOssLibraryDataSource(
    private val libraries: List<OssLibrary> = defaultLibraries,
) : OssLibraryDataSource {
    override suspend fun getOssLibraries(): List<OssLibrary> = libraries

    companion object {
        val defaultLibraries = listOf(
            OssLibrary(
                groupId = "com.squareup.okhttp3",
                artifactId = "okhttp",
                version = "4.12.0",
                name = "OkHttp",
                scm = OssLibrary.Scm(url = "https://github.com/square/okhttp"),
                spdxLicenses = listOf(OssLibrary.License(name = "Apache-2.0")),
            ),
            OssLibrary(
                groupId = "org.jetbrains.kotlinx",
                artifactId = "kotlinx-coroutines-core",
                version = "1.8.0",
                name = "Kotlinx Coroutines",
                scm = OssLibrary.Scm(url = "https://github.com/Kotlin/kotlinx.coroutines"),
                spdxLicenses = listOf(OssLibrary.License(name = "Apache-2.0")),
            ),
            OssLibrary(
                groupId = "com.google.code.gson",
                artifactId = "gson",
                version = "2.10.1",
                name = "Gson",
                scm = OssLibrary.Scm(url = "https://github.com/google/gson"),
                spdxLicenses = listOf(OssLibrary.License(name = "Apache-2.0")),
            ),
            // Duplicate groupId:artifactId — should be deduplicated by AssetOssLibraryDataSource.
            OssLibrary(
                groupId = "com.squareup.okhttp3",
                artifactId = "okhttp",
                version = "5.0.0-alpha",
                name = "OkHttp",
                scm = OssLibrary.Scm(url = "https://github.com/square/okhttp"),
                spdxLicenses = listOf(OssLibrary.License(name = "Apache-2.0")),
            ),
        )
    }
}
