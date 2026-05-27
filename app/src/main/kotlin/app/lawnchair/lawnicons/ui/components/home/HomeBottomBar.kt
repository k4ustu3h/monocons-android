package app.lawnchair.lawnicons.ui.components.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FloatingToolbarDefaults
import androidx.compose.material3.FloatingToolbarDefaults.ScreenOffset
import androidx.compose.material3.FloatingToolbarExitDirection
import androidx.compose.material3.FloatingToolbarScrollBehavior
import androidx.compose.material3.HorizontalFloatingToolbar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipAnchorPosition
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults.rememberTooltipPositionProvider
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import app.lawnchair.lawnicons.R
import app.lawnchair.lawnicons.ui.theme.icon.About
import app.lawnchair.lawnicons.ui.theme.icon.Discord
import app.lawnchair.lawnicons.ui.theme.icon.Github
import app.lawnchair.lawnicons.ui.theme.icon.IconRequest
import app.lawnchair.lawnicons.ui.theme.icon.LawnIcons
import app.lawnchair.lawnicons.ui.theme.icon.OpenCollective
import app.lawnchair.lawnicons.ui.theme.icon.Search
import app.lawnchair.lawnicons.ui.util.Constants
import app.lawnchair.lawnicons.ui.util.PreviewLawnicons
import app.lawnchair.lawnicons.ui.util.PreviewProviders
import app.lawnchair.lawnicons.ui.util.visitUrl

private data class ToolbarItem(
    val icon: ImageVector,
    val labelRes: Int,
    val contentDescriptionRes: Int = labelRes,
    val onClick: () -> Unit,
)

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun BoxScope.HomeBottomBar(
    showIconRequests: Boolean,
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
            icon = LawnIcons.Discord,
            labelRes = R.string.discord,
            onClick = { context.visitUrl(Constants.DISCORD) },
        ),
        ToolbarItem(
            icon = LawnIcons.Github,
            labelRes = R.string.github,
            onClick = { context.visitUrl(Constants.GITHUB) },
        ),
        ToolbarItem(
            icon = LawnIcons.OpenCollective,
            labelRes = R.string.open_collective,
            onClick = { context.visitUrl(Constants.OPEN_COLLECTIVE) },
        ),
        ToolbarItem(
            icon = LawnIcons.IconRequest,
            labelRes = R.string.request_icons,
            onClick = {
                if (showIconRequests) {
                    onNavigateToIconRequest()
                } else {
                    onIconRequestUnavailable()
                }
            },
        ),
        ToolbarItem(
            icon = LawnIcons.About,
            labelRes = R.string.about,
            onClick = onNavigateToAbout,
        ),
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
                        imageVector = LawnIcons.Search,
                        contentDescription = stringResource(id = R.string.search),
                    )
                }
            }
        },
        content = {
            toolbarItems.forEach { item ->
                SimpleTooltipBox(
                    label = stringResource(id = item.labelRes),
                ) {
                    IconButton(onClick = item.onClick) {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = stringResource(id = item.contentDescriptionRes),
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
                LawnIcons.About,
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
