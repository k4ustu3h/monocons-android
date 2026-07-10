package k4ustu3h.monocons.ui.util

import android.content.res.Configuration
import android.graphics.drawable.ColorDrawable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import k4ustu3h.monocons.data.model.Announcement
import k4ustu3h.monocons.data.model.AnnouncementLocation
import k4ustu3h.monocons.data.model.Component
import k4ustu3h.monocons.data.model.IconInfo
import k4ustu3h.monocons.data.model.LabelAndComponent
import k4ustu3h.monocons.data.model.OssLibrary
import k4ustu3h.monocons.data.model.SystemIconInfo
import k4ustu3h.monocons.ui.LawniconsActions
import k4ustu3h.monocons.ui.LocalLawniconsActions
import k4ustu3h.monocons.ui.theme.LawniconsTheme

@Preview(
    name = "Normal",
    showBackground = true,
    wallpaper = Wallpapers.NONE,
)
@Preview(
    name = "Normal Night",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    wallpaper = Wallpapers.NONE,
)
@Preview(
    name = "Themed",
    showBackground = true,
    wallpaper = Wallpapers.RED_DOMINATED_EXAMPLE,
)
@Preview(
    name = "Themed Night",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    wallpaper = Wallpapers.RED_DOMINATED_EXAMPLE,
)
annotation class PreviewLawnicons

@Composable
fun PreviewProviders(content: @Composable () -> Unit) {
    LawniconsTheme {
        CompositionLocalProvider(
            LocalLawniconsActions provides LawniconsActions(false, {}),
        ) {
            content()
        }
    }
}

object SampleData {
    val iconInfoSample = IconInfo(
        drawableName = "@drawable/email",
        componentNames = listOf(
            LabelAndComponent("Email", "com.android.email/.ExampleActivity"),
        ),
        drawableId = 1,
    )

    val iconInfoList = listOf(
        IconInfo(
            drawableName = "@drawable/email",
            componentNames = listOf(
                LabelAndComponent("Email", "com.android.email/.ExampleActivity"),
            ),
            drawableId = 1,
        ),
        IconInfo(
            drawableName = "@drawable/search",
            componentNames = listOf(
                LabelAndComponent("Search", "com.android.search/.ExampleActivity"),
            ),
            drawableId = 2,
        ),
        IconInfo(
            drawableName = "@drawable/phone",
            componentNames = listOf(
                LabelAndComponent("Phone", "com.android.phone/.ExampleActivity"),
            ),
            drawableId = 3,
        ),
    )

    val announcements = listOf(
        Announcement(
            title = "Example",
            description = "Example description",
            icon = "ic_example.svg",
            url = "https://example.com",
            location = AnnouncementLocation.Home,
        ),
        Announcement(
            title = "Example",
            description = "Example description",
            icon = "ic_example.svg",
            url = "https://example.com",
            location = AnnouncementLocation.IconRequest,
        ),
    )

    val iconRequestList = listOf(
        SystemIconInfo(
            label = "Email",
            component = Component(
                "com.android.email",
                "com.android.email/.ExampleActivity",
            ),
            drawable = ColorDrawable(),
        ),
        SystemIconInfo(
            label = "Search",
            component = Component(
                "com.android.search",
                "com.android.search/.ExampleActivity",
            ),
            drawable = ColorDrawable(),
        ),
    )

    val ossLibraries = listOf(
        OssLibrary(
            groupId = "group-1",
            artifactId = "example-library",
            name = "Example Library",
            version = "1",
            scm = OssLibrary.Scm(
                url = "https://example.com",
            ),
            spdxLicenses = listOf(
                OssLibrary.License(
                    name = "Example License",
                ),
            ),
        ),
        OssLibrary(
            groupId = "group-2",
            artifactId = "example-library-2",
            name = "Example Library 2",
            version = "2",
            scm = OssLibrary.Scm(
                url = "https://example.com",
            ),
            spdxLicenses = listOf(
                OssLibrary.License(
                    name = "Example License",
                ),
            ),
        ),
    )
}
