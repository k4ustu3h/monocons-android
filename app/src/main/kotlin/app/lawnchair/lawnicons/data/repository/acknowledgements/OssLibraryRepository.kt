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
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesBinding
import dev.zacsweers.metro.SingleIn
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn

interface OssLibraryRepository {
    val ossLibraries: StateFlow<List<OssLibrary>>
}

@SingleIn(AppScope::class)
@ContributesBinding(AppScope::class)
class OssLibraryRepositoryImpl(
    private val dataSource: OssLibraryDataSource,
) : OssLibraryRepository {

    private val coroutineScope = MainScope()

    override val ossLibraries: StateFlow<List<OssLibrary>> = flow {
        emit(dataSource.getOssLibraries())
    }
        .flowOn(Dispatchers.IO)
        .stateIn(coroutineScope, SharingStarted.Lazily, emptyList())
}
