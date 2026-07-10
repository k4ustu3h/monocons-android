package k4ustu3h.monocons.ui.components.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FloatingToolbarDefaults
import androidx.compose.material3.FloatingToolbarDefaults.ScreenOffset
import androidx.compose.material3.FloatingToolbarExitDirection
import androidx.compose.material3.FloatingToolbarScrollBehavior
import androidx.compose.material3.HorizontalFloatingToolbar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipAnchorPosition
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults.rememberTooltipPositionProvider
import androidx.compose.material3.rememberBottomSheetState
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import k4ustu3h.monocons.BuildConfig
import k4ustu3h.monocons.R
import k4ustu3h.monocons.ui.components.core.SimpleListRow
import k4ustu3h.monocons.ui.theme.icon.About
import k4ustu3h.monocons.ui.theme.icon.Github
import k4ustu3h.monocons.ui.theme.icon.IconRequest
import k4ustu3h.monocons.ui.theme.icon.Monocons
import k4ustu3h.monocons.ui.theme.icon.NewIcons
import k4ustu3h.monocons.ui.theme.icon.Search
import k4ustu3h.monocons.ui.util.Constants
import k4ustu3h.monocons.ui.util.PreviewLawnicons
import k4ustu3h.monocons.ui.util.PreviewProviders
import k4ustu3h.monocons.ui.util.visitUrl
import kotlinx.coroutines.launch

private data class ToolbarItem(
    val icon: ImageVector,
    val label: String,
    val contentDescription: String = label,
    val onClick: () -> Unit,
)

@OptIn(ExperimentalMaterial3ExpressiveApi::class, ExperimentalMaterial3Api::class)
@Composable
fun BoxScope.HomeBottomBar(
    showIconRequests: Boolean,
    showNewIcons: Boolean,
    onNavigateToNewIcons: () -> Unit,
    onNavigateToAbout: () -> Unit,
    onNavigateToIconRequest: () -> Unit,
    onIconRequestUnavailable: () -> Unit,
    onExpandSearch: () -> Unit,
    scrollBehavior: FloatingToolbarScrollBehavior,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current

    val toolbarItems = listOf(
        ToolbarItem(
            icon = Monocons.NewIcons,
            label = stringResource(R.string.new_icons_in_version, BuildConfig.VERSION_NAME),
            onClick = onNavigateToNewIcons,
        ),
        ToolbarItem(
            icon = Monocons.Github,
            label = stringResource(R.string.github),
            onClick = { context.visitUrl(Constants.GITHUB) },
        ),
        ToolbarItem(
            icon = Monocons.About,
            label = stringResource(R.string.about),
            onClick = onNavigateToAbout,
        ),
        ToolbarItem(
            icon = Monocons.IconRequest,
            label = stringResource(R.string.request_icons),
            onClick = {
                if (showIconRequests) {
                    onNavigateToIconRequest()
                } else {
                    onIconRequestUnavailable()
                }
            },
        ),
    ).filter {
        showNewIcons || it.icon != Monocons.NewIcons
    }

    val maxIcons = 4
    val iconItems = toolbarItems.take(maxIcons)
    val overflowItems = toolbarItems.drop(maxIcons).reversed()

    val coroutineScope = rememberCoroutineScope()

    var showBottomSheet by remember { mutableStateOf(false) }
    val bottomSheetState = rememberBottomSheetState(
        initialValue = SheetValue.Hidden,
    )

    HorizontalFloatingToolbar(
        expanded = true,
        scrollBehavior = scrollBehavior,
        floatingActionButton = {
            SimpleTooltipBox(
                label = stringResource(id = R.string.search),
            ) {
                FloatingToolbarDefaults.StandardFloatingActionButton(
                    onClick = onExpandSearch,
                ) {
                    Icon(
                        imageVector = Monocons.Search,
                        contentDescription = stringResource(id = R.string.search),
                    )
                }
            }
        },
        content = {
            iconItems.forEach { item ->
                SimpleTooltipBox(
                    label = item.label,
                ) {
                    IconButton(onClick = item.onClick) {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.contentDescription,
                            modifier = Modifier.requiredSize(24.dp),
                        )
                    }
                }
            }
        },
        modifier = modifier
            .align(Alignment.BottomCenter)
            .offset(y = -ScreenOffset)
            .navigationBarsPadding(),
    )

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet = false
            },
        ) {
            LazyColumn {
                itemsIndexed(overflowItems) { index, it ->
                    SimpleListRow(
                        label = it.label,
                        startIcon = {
                            Icon(
                                imageVector = it.icon,
                                contentDescription = it.contentDescription,
                                modifier = Modifier.requiredSize(24.dp),
                            )
                        },
                        shapes = ListItemDefaults.segmentedShapes(index, overflowItems.size),
                    ) {
                        it.onClick()
                        coroutineScope.launch {
                            bottomSheetState.hide()
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SimpleTooltipBox(
    label: String,
    modifier: Modifier = Modifier,
    content: @Composable (() -> Unit),
) {
    TooltipBox(
        positionProvider = rememberTooltipPositionProvider(TooltipAnchorPosition.Above, 4.dp),
        tooltip = {
            PlainTooltip {
                Text(label)
            }
        },
        state = rememberTooltipState(),
        modifier = modifier,
    ) {
        content()
    }
}

@PreviewLawnicons
@Composable
private fun SimpleTooltipBoxPreview() {
    PreviewProviders {
        SimpleTooltipBox(
            label = "Example",
        ) {
            Icon(
                Monocons.About,
                contentDescription = null,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@PreviewLawnicons
@Composable
private fun HomeBottomBarPreview() {
    PreviewProviders {
        Box {
            HomeBottomBar(
                showIconRequests = true,
                showNewIcons = true,
                onNavigateToNewIcons = {},
                onNavigateToAbout = {},
                onNavigateToIconRequest = {},
                onIconRequestUnavailable = {},
                onExpandSearch = {},
                scrollBehavior = FloatingToolbarDefaults.exitAlwaysScrollBehavior(
                    FloatingToolbarExitDirection.Bottom,
                ),
            )
        }
    }
}
