package k4ustu3h.monocons.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItemShapes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import k4ustu3h.monocons.ui.components.core.ListRowDefaults
import k4ustu3h.monocons.ui.components.core.SimpleListRow
import k4ustu3h.monocons.ui.theme.icon.Check
import k4ustu3h.monocons.ui.theme.icon.Monocons
import k4ustu3h.monocons.ui.util.PreviewLawnicons
import k4ustu3h.monocons.ui.util.PreviewProviders
import k4ustu3h.monocons.ui.util.visitUrl

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun ContributorRow(
    name: String,
    photoUrl: String,
    shapes: ListItemShapes,
    modifier: Modifier = Modifier,
    profileUrl: String? = null,
    socialUrl: String? = null,
    description: String? = null,
    divider: Boolean = true,
) {
    val context = LocalContext.current
    val url = profileUrl ?: socialUrl
    val onClick = if (url != null) {
        { context.visitUrl(url) }
    } else {
        null
    }

    SimpleListRow(
        label = name,
        modifier = modifier,
        description = description,
        startIcon = {
            if (LocalInspectionMode.current) {
                Icon(imageVector = Monocons.Check, contentDescription = null)
            } else {
                AsyncImage(
                    contentDescription = name,
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(data = photoUrl).crossfade(enable = true).build(),
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape),
                )
            }
        },
        divider = divider,
        background = true,
        shapes = shapes,
        onClick = onClick,
    )
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@PreviewLawnicons
@Composable
private fun ContributorRowPreview() {
    PreviewProviders {
        ContributorRow(
            name = "User",
            photoUrl = "https://lawnchair.app/images/lawnchair.png",
            description = "The Lawnchair Logo",
            shapes = ListRowDefaults.singleItemShapes,
        )
    }
}
