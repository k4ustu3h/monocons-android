package k4ustu3h.forkicons.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import dev.zacsweers.metrox.viewmodel.LocalMetroViewModelFactory
import dev.zacsweers.metrox.viewmodel.MetroViewModelFactory
import k4ustu3h.forkicons.data.model.IconInfo
import k4ustu3h.forkicons.ui.destination.about.About
import k4ustu3h.forkicons.ui.destination.about.aboutDestination
import k4ustu3h.forkicons.ui.destination.acknowledgements.Acknowledgements
import k4ustu3h.forkicons.ui.destination.acknowledgements.acknowledgementsDestination
import k4ustu3h.forkicons.ui.destination.contributors.Contributors
import k4ustu3h.forkicons.ui.destination.contributors.contributorsDestination
import k4ustu3h.forkicons.ui.destination.debugmenu.DebugMenu
import k4ustu3h.forkicons.ui.destination.debugmenu.debugMenuDestination
import k4ustu3h.forkicons.ui.destination.home.Home
import k4ustu3h.forkicons.ui.destination.home.homeDestination
import k4ustu3h.forkicons.ui.destination.iconrequest.IconRequest
import k4ustu3h.forkicons.ui.destination.iconrequest.iconRequestDestination
import k4ustu3h.forkicons.ui.destination.newicons.NewIcons
import k4ustu3h.forkicons.ui.destination.newicons.newIconsDestination
import soup.compose.material.motion.animation.materialSharedAxisX
import soup.compose.material.motion.animation.rememberSlideDistance

data class LawniconsActions(
    val isIconPicker: Boolean = false,
    val onSendResult: (IconInfo) -> Unit = {},
)

val LocalLawniconsActions = staticCompositionLocalOf { LawniconsActions() }

@Composable
fun Lawnicons(
    metroVmf: MetroViewModelFactory,
    isExpandedScreen: Boolean,
    onSendResult: (IconInfo) -> Unit,
    modifier: Modifier = Modifier,
    isIconPicker: Boolean = false,
) {
    CompositionLocalProvider(
        LocalMetroViewModelFactory provides metroVmf,
        LocalLawniconsActions provides LawniconsActions(isIconPicker, onSendResult),
    ) {
        val navigationState = rememberNavigationState(
            startRoute = Home,
            topLevelRoutes = setOf(Home, About, NewIcons, IconRequest, DebugMenu),
        )
        val navigator = remember { Navigator(navigationState) }
        val isRtl = LocalLayoutDirection.current == LayoutDirection.Rtl
        val slideDistance = rememberSlideDistance()

        val entryProvider = entryProvider {
            homeDestination(
                onNavigateToAbout = { navigator.navigate(About) },
                onNavigateToNewIcons = { navigator.navigate(NewIcons) },
                onNavigateToIconRequest = { navigator.navigate(IconRequest) },
                onNavigateToDebugMenu = { navigator.navigate(DebugMenu) },
                isExpandedScreen = isExpandedScreen,
            )
            debugMenuDestination(
                isExpandedScreen = isExpandedScreen,
                onBack = navigator::goBack,
            )
            iconRequestDestination(
                isExpandedScreen = isExpandedScreen,
                onBack = navigator::goBack,
            )
            acknowledgementsDestination(
                onBack = navigator::goBack,
                isExpandedScreen = isExpandedScreen,
            )
            aboutDestination(
                onBack = navigator::goBack,
                onNavigateToContributors = {
                    navigator.navigate(Contributors)
                },
                onNavigateToAcknowledgements = {
                    navigator.navigate(Acknowledgements)
                },
                isExpandedScreen = isExpandedScreen,
            )
            contributorsDestination(
                onBack = navigator::goBack,
                isExpandedScreen = isExpandedScreen,
            )
            newIconsDestination(
                onBack = navigator::goBack,
                isExpandedScreen = isExpandedScreen,
            )
        }

        Surface(
            modifier = modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            NavDisplay(
                entries = navigationState.toEntries(entryProvider),
                onBack = navigator::goBack,
                transitionSpec = { materialSharedAxisX(!isRtl, slideDistance) },
                popTransitionSpec = { materialSharedAxisX(isRtl, slideDistance) },
                predictivePopTransitionSpec = { materialSharedAxisX(isRtl, slideDistance) },
            )
        }
    }
}
