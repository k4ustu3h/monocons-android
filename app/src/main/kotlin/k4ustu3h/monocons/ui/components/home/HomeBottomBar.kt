package k4ustu3h.monocons.ui.components.home

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import k4ustu3h.monocons.R
import k4ustu3h.monocons.ui.theme.icon.About
import k4ustu3h.monocons.ui.theme.icon.Monocons
import k4ustu3h.monocons.ui.theme.icon.Github
import k4ustu3h.monocons.ui.theme.icon.IconRequest
import k4ustu3h.monocons.ui.theme.icon.Search
import k4ustu3h.monocons.ui.util.Constants
import k4ustu3h.monocons.ui.util.PreviewLawnicons
import k4ustu3h.monocons.ui.util.PreviewProviders
import k4ustu3h.monocons.ui.util.visitUrl

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun BoxScope.HomeBottomToolbar(
    showIconRequests: Boolean,
    onNavigateToAbout: () -> Unit,
    onNavigateToIconRequest: () -> Unit,
    onIconRequestUnavailable: () -> Unit,
    onExpandSearch: () -> Unit,
    scrollBehavior: FloatingToolbarScrollBehavior,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current

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
            SimpleTooltipBox(
                label = stringResource(id = R.string.github),
            ) {
                IconButton(
                    onClick = {
                        context.visitUrl(Constants.GITHUB)
                    },
                ) {
                    Icon(
                        imageVector = Monocons.Github,
                        contentDescription = stringResource(id = R.string.github),
                        modifier = Modifier.requiredSize(24.dp),
                    )
                }
            }

            SimpleTooltipBox(
                label = stringResource(R.string.request_icons),
            ) {
                IconButton(
                    onClick = {
                        if (showIconRequests) {
                            onNavigateToIconRequest()
                        } else {
                            onIconRequestUnavailable()
                        }
                    },
                ) {
                    Icon(
                        imageVector = Monocons.IconRequest,
                        contentDescription = stringResource(id = R.string.request_icons),
                        modifier = Modifier.requiredSize(24.dp),
                    )
                }
            }

            SimpleTooltipBox(
                label = stringResource(id = R.string.about),
            ) {
                IconButton(onClick = onNavigateToAbout) {
                    Icon(
                        imageVector = Monocons.About,
                        contentDescription = stringResource(id = R.string.about),
                        modifier = Modifier.requiredSize(24.dp),
                    )
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
                Monocons.About,
                contentDescription = null,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@PreviewLawnicons
@Composable
private fun HomeBottomToolbarPreview() {
    PreviewProviders {
        Box {
            HomeBottomToolbar(
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
