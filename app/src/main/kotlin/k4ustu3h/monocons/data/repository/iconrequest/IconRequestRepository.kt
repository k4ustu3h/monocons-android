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

package k4ustu3h.monocons.data.repository.iconrequest

import android.app.Application
import android.util.Log
import dev.zacsweers.metro.ContributesBinding
import dev.zacsweers.metro.SingleIn
import java.io.File
import k4ustu3h.monocons.LawniconsScope
import k4ustu3h.monocons.data.api.IconRequestSettingsAPI
import k4ustu3h.monocons.data.model.IconInfo
import k4ustu3h.monocons.data.model.IconRequestModel
import k4ustu3h.monocons.data.model.SystemIconInfo
import k4ustu3h.monocons.data.repository.PreferenceManager
import k4ustu3h.monocons.data.repository.home.getIconInfo
import k4ustu3h.monocons.util.isIzzyBuild
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

interface IconRequestRepository {
    val iconRequestList: StateFlow<IconRequestModel?>
    val isEnabled: StateFlow<Boolean>

    suspend fun refresh()
    suspend fun createIconRequestZip(currentIconRequests: List<SystemIconInfo>?): File?
}

@SingleIn(LawniconsScope::class)
@ContributesBinding(LawniconsScope::class)
class IconRequestRepositoryImpl(
    val application: Application,
    private val api: IconRequestSettingsAPI,
    private val preferenceManager: PreferenceManager,
) : IconRequestRepository {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private val _iconRequestList = MutableStateFlow<IconRequestModel?>(null)
    override val iconRequestList = _iconRequestList.asStateFlow()

    private val _isEnabled = MutableStateFlow(false)
    override val isEnabled = _isEnabled.asStateFlow()

    init {
        coroutineScope.launch {
            refresh()
        }
    }

    override suspend fun refresh() {
        withContext(Dispatchers.IO) {
            val enabledDeferred = async {
                val apiEnabled = if (!isIzzyBuild) {
                    try {
                        api.getIconRequestSettings().enabled
                    } catch (_: Exception) {
                        false
                    }
                } else {
                    true
                }

                val forceEnabled = preferenceManager.forceEnableIconRequest.get()
                apiEnabled || forceEnabled
            }

            val iconList = application.getIconInfo().sortedBy { it.label.lowercase() }
            val systemPackageList =
                application.getSystemIconInfo().sortedBy { it.label.lowercase() }

            _isEnabled.value = enabledDeferred.await()

            updateIconRequestList(iconList, systemPackageList)
        }
    }

    private suspend fun updateIconRequestList(
        lawniconsIconList: List<IconInfo>,
        systemPackageList: List<SystemIconInfo>,
    ) = withContext(Dispatchers.Default) {
        val themedComponentStrings = lawniconsIconList.flatMap { it.componentNames }
            .map { it.componentName.flattenToString() }.toSet()

        val unthemedApps = systemPackageList.filter { systemApp ->
            systemApp.componentName.flattenToString() !in themedComponentStrings
        }

        _iconRequestList.value = IconRequestModel(
            list = unthemedApps,
            iconCount = unthemedApps.size,
        )
    }

    override suspend fun createIconRequestZip(currentIconRequests: List<SystemIconInfo>?): File? {
        if (currentIconRequests.isNullOrEmpty()) {
            Log.d("IconRequestRepo", "No icon requests to bundle.")
            return null
        }
        return IconRequestBundler.createIconRequestZip(application, currentIconRequests)
    }
}
