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

package k4ustu3h.monocons.data.repository.acknowledgements

import android.app.Application
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesBinding
import k4ustu3h.monocons.data.kotlinxJson
import k4ustu3h.monocons.data.model.OssLibrary

interface OssLibraryDataSource {
    suspend fun getOssLibraries(): List<OssLibrary>
}

@ContributesBinding(AppScope::class)
class AssetOssLibraryDataSource(
    private val application: Application,
) : OssLibraryDataSource {
    override suspend fun getOssLibraries(): List<OssLibrary> {
        val jsonString = application.resources.assets.open("licenses.json").bufferedReader()
            .use { it.readText() }

        return kotlinxJson.decodeFromString<List<OssLibrary>>(jsonString).asSequence()
            .distinctBy { "${it.groupId}:${it.artifactId}" }
            .distinctBy { "${it.groupId}:${it.name}" } // Handle cases with same name but different artifactId.
            .sortedBy { it.name }.toList()
    }
}
