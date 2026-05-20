package k4ustu3h.monocons.ui.components.core

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.LargeFlexibleTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import k4ustu3h.monocons.ui.components.home.NavigationIconButton
import k4ustu3h.monocons.ui.theme.adaptiveSurfaceColor
import k4ustu3h.monocons.ui.theme.icon.Back
import k4ustu3h.monocons.ui.theme.icon.Monocons
import k4ustu3h.monocons.ui.util.PreviewLawnicons
import k4ustu3h.monocons.ui.util.PreviewProviders

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun TopAppBar(
    navigationIcon: @Composable () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
    title: String,
    modifier: Modifier = Modifier,
    isExpandedScreen: Boolean = false,
    containerColor: Color = adaptiveSurfaceColor,
) {
    val topAppBarColors = TopAppBarDefaults.topAppBarColors().copy(
        containerColor = containerColor,
        scrolledContainerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
    )

    if (!isExpandedScreen) {
        LargeFlexibleTopAppBar(
            modifier = modifier,
            scrollBehavior = scrollBehavior,
            navigationIcon = navigationIcon,
            title = {
                Text(title)
            },
            colors = topAppBarColors,
        )
    } else {
        TopAppBar(
            modifier = modifier,
            scrollBehavior = scrollBehavior,
            navigationIcon = navigationIcon,
            title = {
                Text(title)
            },
            colors = topAppBarColors,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@PreviewLawnicons
@Composable
private fun SmallTopAppBarPreview() {
    PreviewProviders {
        TopAppBar(
            navigationIcon = {
                NavigationIconButton(
                    imageVector = Monocons.Back,
                    onClick = {},
                    size = 40.dp,
                    modifier = Modifier.padding(horizontal = 4.dp),
                )
            },
            scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
            title = "Example",
            isExpandedScreen = true,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@PreviewLawnicons
@Composable
private fun LargeTopAppBarPreview() {
    PreviewProviders {
        TopAppBar(
            navigationIcon = {
                NavigationIconButton(
                    imageVector = Monocons.Back,
                    onClick = {},
                    size = 40.dp,
                    modifier = Modifier.padding(horizontal = 4.dp),
                )
            },
            scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(),
            title = "Example",
            isExpandedScreen = false,
        )
    }
}
