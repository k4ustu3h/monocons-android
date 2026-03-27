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

package k4ustu3h.forkicons.ui.destination.debugmenu

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import dev.zacsweers.metrox.viewmodel.metroViewModel
import k4ustu3h.forkicons.data.model.IconInfoModel
import k4ustu3h.forkicons.data.model.IconRequestModel
import k4ustu3h.forkicons.data.model.getFirstLabelAndComponent
import k4ustu3h.forkicons.data.model.splitByComponentName
import k4ustu3h.forkicons.data.repository.BasePreferenceManager
import k4ustu3h.forkicons.data.repository.DummySharedPreferences
import k4ustu3h.forkicons.data.repository.PreferenceManager
import k4ustu3h.forkicons.ui.components.core.LawniconsScaffold
import k4ustu3h.forkicons.ui.components.core.ListRow
import k4ustu3h.forkicons.ui.components.core.ListRowDefaults
import k4ustu3h.forkicons.ui.components.core.ListRowLabel
import k4ustu3h.forkicons.ui.components.core.SimpleListRow
import k4ustu3h.forkicons.ui.theme.adaptiveSurfaceContainerColor
import k4ustu3h.forkicons.ui.theme.icon.Copy
import k4ustu3h.forkicons.ui.theme.icon.Forkicons
import k4ustu3h.forkicons.ui.util.PreviewLawnicons
import k4ustu3h.forkicons.ui.util.PreviewProviders
import k4ustu3h.forkicons.ui.util.SampleData
import k4ustu3h.forkicons.ui.util.copyTextToClipboard
import kotlinx.serialization.Serializable

@Serializable
data object DebugMenu : NavKey

fun EntryProviderScope<NavKey>.debugMenuDestination(
    onBack: () -> Unit,
    isExpandedScreen: Boolean = false,
) {
    entry<DebugMenu> {
        DebugMenu(
            onBack = onBack,
            isExpandedScreen = isExpandedScreen,
        )
    }
}

// screen to view statistics and other internal info
@Composable
fun DebugMenu(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DebugMenuViewModel = metroViewModel(),
    isExpandedScreen: Boolean = false,
) {
    val iconInfoModel by viewModel.iconInfoModel.collectAsStateWithLifecycle()
    val newIconsModel by viewModel.newIconsModel.collectAsStateWithLifecycle()
    val iconRequestList by viewModel.iconRequestList.collectAsStateWithLifecycle()

    val context = LocalContext.current
    val prefs = viewModel.preferenceManager

    val debugList = listOf(
        ListItemContent("Icon count", iconInfoModel.iconCount),
        ListItemContent("New icons count", newIconsModel.iconCount),
        ListItemContent("Component count", iconInfoModel.iconInfo.splitByComponentName().size),
        ListItemContent("Icon request count", iconRequestList?.iconCount ?: 0),
    )

    val boolPrefs = listOf(
        prefs.forceEnableIconRequest,
    )

    DebugMenuScreen(
        newIconsModel = newIconsModel,
        iconRequestList = iconRequestList,
        debugList = debugList,
        boolPrefs = boolPrefs,
        onCopy = {
            context.copyTextToClipboard(it)
        },
        isExpandedScreen = isExpandedScreen,
        onBack = onBack,
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
private fun DebugMenuScreen(
    newIconsModel: IconInfoModel,
    iconRequestList: IconRequestModel?,
    debugList: List<ListItemContent>,
    boolPrefs: List<BasePreferenceManager.BoolPref>,
    onCopy: (String) -> Unit,
    isExpandedScreen: Boolean,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    LawniconsScaffold(
        title = "Debug menu",
        onBack = onBack,
        isExpandedScreen = isExpandedScreen,
        modifier = modifier,
    ) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues,
        ) {
            itemsIndexed(debugList) { index, it ->
                SimpleListRow(
                    label = it.label,
                    description = it.value.toString(),
                    background = true,
                    shapes = ListItemDefaults.segmentedShapes(index, debugList.size),
                ) {
                    onCopy("${it.label}: ${it.value}")
                }
            }
            item {
                Spacer(Modifier.height(4.dp))
            }
            itemsIndexed(boolPrefs) { index, pref ->
                ListItem(
                    checked = pref.asState().value,
                    onCheckedChange = pref::set,
                    content = {
                        ListRowLabel(pref.key)
                    },
                    colors = ListItemDefaults.colors(
                        containerColor = adaptiveSurfaceContainerColor,
                    ),
                    modifier = Modifier.padding(horizontal = ListRowDefaults.basePadding),
                    trailingContent = {
                        Switch(
                            checked = pref.asState().value,
                            onCheckedChange = pref::set,
                            interactionSource = null,
                        )
                    },
                    shapes = ListItemDefaults.segmentedShapes(
                        index,
                        boolPrefs.size,
                        ListRowDefaults.singleItemShapes,
                    ),
                )
            }
            item {
                Spacer(Modifier.height(4.dp))
            }
            item {
                ListHeader(
                    "Unthemed icons",
                    {
                        onCopy(iconRequestList?.list.toString())
                    },
                )
            }
            items(iconRequestList?.list ?: emptyList()) {
                ListRow(
                    label = {
                        Text(
                            text = "${it.label}\n${it.componentName.flattenToString()}",
                            fontFamily = FontFamily.Monospace,
                        )
                    },
                )
            }
            item {
                Spacer(Modifier.height(8.dp))
            }
            item {
                ListHeader(
                    "New icons list",
                    {
                        onCopy(
                            newIconsModel.iconInfo.splitByComponentName().toString(),
                        )
                    },
                )
            }
            items(newIconsModel.iconInfo.splitByComponentName()) {
                val labelAndComponent = it.getFirstLabelAndComponent()

                ListRow(
                    label = {
                        Text(
                            text = "${labelAndComponent.label}\n${labelAndComponent.componentName.flattenToString()}",
                            fontFamily = FontFamily.Monospace,
                        )
                    },
                )
            }
        }
    }
}

data class ListItemContent(
    val label: String,
    val value: Int,
)

@Composable
private fun ListHeader(
    label: String,
    onCopy: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
    ) {
        Text(
            label,
            style = MaterialTheme.typography.headlineSmall,
        )
        Spacer(modifier = Modifier.weight(0.05f))
        IconButton(
            onClick = onCopy,
        ) {
            Icon(
                imageVector = Forkicons.Copy,
                contentDescription = null,
            )
        }
    }
}

@PreviewLawnicons
@Composable
private fun DebugMenuScreenPreview() {
    PreviewProviders {
        val prefs = PreferenceManager(DummySharedPreferences())

        DebugMenuScreen(
            newIconsModel = IconInfoModel(
                iconInfo = SampleData.iconInfoList,
            ),
            iconRequestList = IconRequestModel(
                list = SampleData.iconRequestList,
                iconCount = 1,
            ),
            debugList = listOf(
                ListItemContent("Icon count", 100),
            ),
            boolPrefs = listOf(
                prefs.forceEnableIconRequest,
            ),
            onCopy = {},
            isExpandedScreen = true,
            onBack = {},
        )
    }
}
