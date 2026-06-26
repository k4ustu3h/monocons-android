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

@file:OptIn(ExperimentalMaterial3ExpressiveApi::class)

package k4ustu3h.monocons.ui.destination.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import k4ustu3h.monocons.BuildConfig
import k4ustu3h.monocons.R
import k4ustu3h.monocons.ui.components.ContributorRow
import k4ustu3h.monocons.ui.components.core.LawniconsScaffold
import k4ustu3h.monocons.ui.components.core.ListRowDefaults
import k4ustu3h.monocons.ui.components.core.SimpleListRow
import k4ustu3h.monocons.ui.theme.icon.AppIcon
import k4ustu3h.monocons.ui.theme.icon.Check
import k4ustu3h.monocons.ui.theme.icon.Lawnicons
import k4ustu3h.monocons.ui.theme.icon.Monocons
import k4ustu3h.monocons.ui.util.Constants
import k4ustu3h.monocons.ui.util.PreviewLawnicons
import k4ustu3h.monocons.ui.util.PreviewProviders
import k4ustu3h.monocons.ui.util.visitUrl
import kotlinx.serialization.Serializable

enum class ColumnTypes {
    SPACER,
    HEADER,
    NAVIGATION_ITEM,
    LIST_ITEM,
}

@Serializable
data object About : NavKey

fun EntryProviderScope<NavKey>.aboutDestination(
    onBack: () -> Unit,
    onNavigateToContributors: () -> Unit,
    onNavigateToAcknowledgements: () -> Unit,
    isExpandedScreen: Boolean,
) {
    entry<About> {
        About(
            onBack = onBack,
            onNavigateToContributors = onNavigateToContributors,
            onNavigateToAcknowledgements = onNavigateToAcknowledgements,
            isExpandedScreen = isExpandedScreen,
        )
    }
}

@Composable
private fun About(
    onBack: () -> Unit,
    onNavigateToContributors: () -> Unit,
    onNavigateToAcknowledgements: () -> Unit,
    isExpandedScreen: Boolean,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val lawniconsUrl = "https://github.com/LawnchairLauncher/lawnicons/releases/tag/v"

    LawniconsScaffold(
        modifier = modifier,
        title = stringResource(id = R.string.about),
        onBack = onBack,
        isExpandedScreen = isExpandedScreen,
    ) { paddingValues ->
        val layoutDirection = LocalLayoutDirection.current
        val verticalListPadding = 8.dp
        LazyColumn(
            contentPadding = PaddingValues(
                start = paddingValues.calculateStartPadding(layoutDirection),
                top = paddingValues.calculateTopPadding() + verticalListPadding,
                end = paddingValues.calculateEndPadding(layoutDirection),
                bottom = paddingValues.calculateBottomPadding() + verticalListPadding,
            ),
        ) {
            item {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 8.dp,
                            bottom = 32.dp,
                        ),
                ) {
                    if (LocalInspectionMode.current) {
                        Icon(
                            imageVector = Monocons.Check,
                            contentDescription = null,
                            modifier = Modifier.size(72.dp),
                        )
                    } else {
                        Image(
                            imageVector = Monocons.AppIcon,
                            contentDescription = stringResource(id = R.string.app_name),
                            modifier = Modifier
                                .size(72.dp)
                                .clip(CircleShape),
                        )
                    }
                    Text(
                        text = stringResource(id = R.string.app_name),
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.padding(top = 12.dp),
                    )
                    Text(
                        text = stringResource(id = R.string.version_x, BuildConfig.VERSION_NAME),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }
            item(contentType = ColumnTypes.HEADER) {
                ListHeader(stringResource(id = R.string.core_contributors))
            }
            itemsIndexed(
                coreContributors,
                contentType = { _, _ -> ColumnTypes.LIST_ITEM },
            ) { index, it ->
                ContributorRow(
                    name = it.name,
                    id = it.id,
                    photoUrl = it.photoUrl,
                    profileUrl = it.socialUrl,
                    divider = index != coreContributors.lastIndex,
                    description = it.descriptionRes?.let { stringResource(id = it) },
                    shapes = ListItemDefaults.segmentedShapes(index, coreContributors.size),
                )
            }
            item(contentType = ColumnTypes.SPACER) {
                Spacer(Modifier.height(16.dp))
            }
            item(contentType = ColumnTypes.HEADER) {
                ListHeader(stringResource(id = R.string.app_name) + " " + stringResource(id = R.string.core_contributors).lowercase())
            }
            itemsIndexed(
                monoconsContributors,
                contentType = { _, _ -> ColumnTypes.LIST_ITEM },
            ) { index, it ->
                ContributorRow(
                    name = it.name,
                    id = it.id,
                    photoUrl = it.photoUrl,
                    profileUrl = it.socialUrl,
                    divider = index != monoconsContributors.lastIndex,
                    description = it.descriptionRes?.let { stringResource(id = it) },
                    shapes = ListRowDefaults.singleItemShapes,
                )
            }
            item(contentType = ColumnTypes.SPACER) {
                Spacer(Modifier.height(8.dp))
            }
            item(contentType = ColumnTypes.NAVIGATION_ITEM) {
                SimpleListRow(
                    label = stringResource(id = R.string.see_all_contributors),
                    background = true,
                    onClick = onNavigateToContributors,
                )
            }
            item(contentType = ColumnTypes.SPACER) {
                Spacer(Modifier.height(16.dp))
            }
            item(contentType = ColumnTypes.HEADER) {
                ListHeader(stringResource(id = R.string.special_thanks))
            }
            itemsIndexed(
                specialThanks,
                contentType = { _, _ -> ColumnTypes.LIST_ITEM },
            ) { index, it ->
                ContributorRow(
                    name = it.name,
                    id = it.id,
                    photoUrl = it.photoUrl,
                    profileUrl = it.username?.let { "https://github.com/$it" },
                    description = it.descriptionRes?.let { stringResource(id = it) },
                    divider = index != specialThanks.lastIndex,
                    socialUrl = it.socialUrl,
                    shapes = ListRowDefaults.singleItemShapes,
                )
            }
            item(contentType = ColumnTypes.SPACER) {
                Spacer(Modifier.height(8.dp))
            }
            item {
                SimpleListRow(
                    onClick = { context.visitUrl(lawniconsUrl + Constants.LAWNICONS_VERSION) },
                    label = stringResource(R.string.lawnicons) + " " + stringResource(
                        id = R.string.version_x,
                        Constants.LAWNICONS_VERSION,
                    ).lowercase(),
                    divider = false,
                    background = true,
                    startIcon = {
                        Image(
                            imageVector = Monocons.Lawnicons,
                            contentDescription = stringResource(id = R.string.app_name),
                            modifier = Modifier.size(32.dp).clip(CircleShape),
                        )
                    },
                )
            }
            item(contentType = ColumnTypes.SPACER) {
                Spacer(Modifier.height(8.dp))
            }
            item(contentType = ColumnTypes.NAVIGATION_ITEM) {
                SimpleListRow(
                    label = stringResource(id = R.string.acknowledgements),
                    background = true,
                    onClick = onNavigateToAcknowledgements,
                )
            }
        }
    }
}

@Composable
private fun ListHeader(label: String, modifier: Modifier = Modifier) {
    Text(
        text = label,
        style = MaterialTheme.typography.titleSmall,
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier.padding(start = 32.dp, bottom = 8.dp),
    )
}

private val coreContributors = listOf(
    Contributor(
        name = "Suphon T.",
        username = "paphonb",
        id = 8080853,
        photoUrl = "https://avatars.githubusercontent.com/u/8080853",
        socialUrl = "https://x.com/paphonb",
        descriptionRes = R.string.contribution_core,
    ),
    Contributor(
        name = "SuperDragonXD",
        username = "SuperDragonXD",
        id = 70206496,
        photoUrl = "https://avatars.githubusercontent.com/u/70206496",
        socialUrl = "https://github.com/SuperDragonXD",
        descriptionRes = R.string.contribution_core,
    ),
    Contributor(
        name = "Patryk Radziszewski",
        username = "Chefski",
        id = 100310118,
        photoUrl = "https://avatars.githubusercontent.com/u/100310118",
        socialUrl = "https://github.com/Chefski",
        descriptionRes = R.string.contribution_icons,
    ),
    Contributor(
        name = "Gleb",
        username = "x9136",
        id = 60105060,
        photoUrl = "https://avatars.githubusercontent.com/u/60105060",
        socialUrl = "https://github.com/x9136",
        descriptionRes = R.string.contribution_icons,
    ),
    Contributor(
        name = "Grabster",
        username = "Grabstertv",
        id = 49114212,
        photoUrl = "https://avatars.githubusercontent.com/u/49114212",
        socialUrl = "https://x.com/grabstertv",
        descriptionRes = R.string.contribution_icons,
    ),
    Contributor(
        name = "Zongle Wang",
        username = "Goooler",
        id = 10363352,
        photoUrl = "https://avatars.githubusercontent.com/u/10363352",
        socialUrl = "https://github.com/Goooler",
        descriptionRes = R.string.contribution_infra,
    ),
)

private val monoconsContributors = listOf(
    Contributor(
        name = "Kaustubh Ladiya",
        username = "k4ustu3h",
        id = 49553711,
        photoUrl = "https://avatars.githubusercontent.com/u/49553711",
        socialUrl = "https://k4ustu3h.vercel.app/",
        descriptionRes = R.string.contribution_core,
    ),
)

private val specialThanks = listOf(
    Contributor(
        name = "Radek Bledowski",
        username = "RadekBledowski",
        id = 22264125,
        photoUrl = "https://avatars.githubusercontent.com/u/22264125",
        socialUrl = "https://x.com/rkbdi",
        descriptionRes = R.string.special_thanks_restoration,
    ),
)

@PreviewLawnicons
@Composable
private fun AboutPreview() {
    PreviewProviders {
        About(
            {},
            {},
            {},
            false,
        )
    }
}

@PreviewLawnicons
@Composable
private fun AboutPreviewExpanded() {
    PreviewProviders {
        About(
            {},
            {},
            {},
            true,
        )
    }
}
