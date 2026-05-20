/*
 * Copyright 2025 Lawnchair Launcher
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

package k4ustu3h.monocons.ui.destination.debugmenu

import androidx.lifecycle.ViewModel
import dev.zacsweers.metro.ContributesIntoMap
import dev.zacsweers.metrox.viewmodel.ViewModelKey
import k4ustu3h.monocons.LawniconsScope
import k4ustu3h.monocons.data.repository.NewIconsRepository
import k4ustu3h.monocons.data.repository.PreferenceManager
import k4ustu3h.monocons.data.repository.home.IconRepository
import k4ustu3h.monocons.data.repository.iconrequest.IconRequestRepository

@ViewModelKey(DebugMenuViewModel::class)
@ContributesIntoMap(LawniconsScope::class)
class DebugMenuViewModel(
    iconRepository: IconRepository,
    newIconsRepository: NewIconsRepository,
    iconRequestRepository: IconRequestRepository,
    val preferenceManager: PreferenceManager,
) : ViewModel() {
    val iconInfoModel = iconRepository.iconInfoModel
    val newIconsModel = newIconsRepository.newIconsInfoModel
    val iconRequestList = iconRequestRepository.iconRequestList
}
