package k4ustu3h.monocons.ui.theme.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Monocons.AppIcon: ImageVector
    get() {
        if (_AppIcon != null) {
            return _AppIcon!!
        }
        _AppIcon = ImageVector.Builder(
            name = "Forkicons",
            defaultWidth = 108.dp,
            defaultHeight = 108.dp,
            viewportWidth = 108f,
            viewportHeight = 108f,
        ).apply {
            path(fill = SolidColor(Color(0xFFFFD600))) {
                moveToRelative(39.23f, 0f)
                arcToRelative(
                    54f,
                    54f,
                    0f,
                    isMoreThanHalf = false,
                    isPositiveArc = false,
                    -3.23f,
                    18f,
                )
                arcToRelative(54f, 54f, 0f, isMoreThanHalf = false, isPositiveArc = false, 54f, 54f)
                arcToRelative(
                    54f,
                    54f,
                    0f,
                    isMoreThanHalf = false,
                    isPositiveArc = false,
                    18f,
                    -3.21f,
                )
                verticalLineToRelative(-68.79f)
                horizontalLineToRelative(-68.77f)
                close()
            }
            path(fill = SolidColor(Color(0xFFD50000))) {
                moveToRelative(90f, 36f)
                arcToRelative(
                    54f,
                    54f,
                    0f,
                    isMoreThanHalf = false,
                    isPositiveArc = false,
                    -54f,
                    54f,
                )
                arcToRelative(
                    54f,
                    54f,
                    0f,
                    isMoreThanHalf = false,
                    isPositiveArc = false,
                    3.21f,
                    18f,
                )
                horizontalLineToRelative(68.79f)
                verticalLineToRelative(-68.91f)
                arcToRelative(
                    54f,
                    54f,
                    0f,
                    isMoreThanHalf = false,
                    isPositiveArc = false,
                    -18f,
                    -3.09f,
                )
                close()
            }
            path(fill = SolidColor(Color(0xFF00C853))) {
                moveToRelative(18f, 36f)
                arcToRelative(
                    54f,
                    54f,
                    0f,
                    isMoreThanHalf = false,
                    isPositiveArc = false,
                    -18f,
                    3.23f,
                )
                verticalLineToRelative(68.77f)
                horizontalLineToRelative(68.79f)
                arcToRelative(
                    54f,
                    54f,
                    0f,
                    isMoreThanHalf = false,
                    isPositiveArc = false,
                    3.21f,
                    -18f,
                )
                arcToRelative(
                    54f,
                    54f,
                    0f,
                    isMoreThanHalf = false,
                    isPositiveArc = false,
                    -54f,
                    -54f,
                )
                close()
            }
            path(fill = SolidColor(Color(0xFF2962FF))) {
                moveToRelative(0f, 0f)
                verticalLineToRelative(68.77f)
                arcToRelative(
                    54f,
                    54f,
                    0f,
                    isMoreThanHalf = false,
                    isPositiveArc = false,
                    18f,
                    3.23f,
                )
                arcToRelative(
                    54f,
                    54f,
                    0f,
                    isMoreThanHalf = false,
                    isPositiveArc = false,
                    54f,
                    -54f,
                )
                arcToRelative(
                    54f,
                    54f,
                    0f,
                    isMoreThanHalf = false,
                    isPositiveArc = false,
                    -3.1f,
                    -18f,
                )
                horizontalLineToRelative(-68.9f)
                close()
            }
            path(fill = SolidColor(Color.White)) {
                moveToRelative(58.21f, 57.75f)
                horizontalLineToRelative(16.58f)
                curveToRelative(0.95f, 0f, 1.71f, 0.76f, 1.71f, 1.71f)
                verticalLineToRelative(16.58f)
                curveToRelative(0f, 0.95f, -0.76f, 1.71f, -1.71f, 1.71f)
                horizontalLineToRelative(-16.58f)
                curveToRelative(-0.95f, 0f, -1.71f, -0.76f, -1.71f, -1.71f)
                verticalLineToRelative(-16.58f)
                curveToRelative(0f, -0.95f, 0.76f, -1.71f, 1.71f, -1.71f)
                close()
                moveTo(29f, 52.75f)
                lineTo(41.5f, 30.25f)
                lineTo(54f, 52.75f)
                close()
                moveTo(60.88f, 31.5f)
                curveToRelative(-1.96f, 0f, -3.59f, 0.65f, -4.91f, 1.94f)
                curveToRelative(-1.31f, 1.29f, -1.97f, 2.88f, -1.97f, 4.75f)
                curveToRelative(0f, 1.04f, 0.21f, 2.02f, 0.63f, 2.94f)
                curveToRelative(0.42f, 0.92f, 1.1f, 1.9f, 2.06f, 2.94f)
                curveToRelative(0.96f, 1.04f, 2.24f, 2.25f, 3.84f, 3.63f)
                curveToRelative(1.6f, 1.38f, 3.59f, 3.06f, 5.97f, 5.06f)
                curveToRelative(2.38f, -2f, 4.36f, -3.69f, 5.97f, -5.06f)
                curveToRelative(1.6f, -1.38f, 2.89f, -2.58f, 3.84f, -3.63f)
                curveToRelative(0.96f, -1.04f, 1.65f, -2.02f, 2.06f, -2.94f)
                curveToRelative(0.42f, -0.92f, 0.63f, -1.9f, 0.63f, -2.94f)
                curveToRelative(0f, -1.88f, -0.66f, -3.46f, -1.97f, -4.75f)
                curveToRelative(-1.31f, -1.29f, -2.95f, -1.94f, -4.91f, -1.94f)
                curveToRelative(-1.13f, 0f, -2.18f, 0.26f, -3.16f, 0.78f)
                curveToRelative(-0.98f, 0.52f, -1.8f, 1.24f, -2.47f, 2.16f)
                curveToRelative(-0.67f, -0.92f, -1.49f, -1.64f, -2.47f, -2.16f)
                curveToRelative(-0.98f, -0.52f, -2.03f, -0.78f, -3.16f, -0.78f)
                close()
                moveTo(41.5f, 57.75f)
                curveToRelative(-2.75f, 0f, -5.1f, 0.98f, -7.06f, 2.94f)
                curveToRelative(-1.96f, 1.96f, -2.94f, 4.31f, -2.94f, 7.06f)
                curveToRelative(0f, 2.75f, 0.98f, 5.1f, 2.94f, 7.06f)
                curveToRelative(1.96f, 1.96f, 4.31f, 2.94f, 7.06f, 2.94f)
                curveToRelative(2.75f, 0f, 5.1f, -0.98f, 7.06f, -2.94f)
                curveToRelative(1.96f, -1.96f, 2.94f, -4.31f, 2.94f, -7.06f)
                curveToRelative(0f, -2.75f, -0.98f, -5.1f, -2.94f, -7.06f)
                curveToRelative(-1.96f, -1.96f, -4.31f, -2.94f, -7.06f, -2.94f)
                close()
            }
        }.build()

        return _AppIcon!!
    }

@Suppress("ObjectPropertyName")
private var _AppIcon: ImageVector? = null
