package k4ustu3h.monocons.ui.theme.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Monocons.NewIcons: ImageVector
    get() {
        if (_New != null) {
            return _New!!
        }
        _New = ImageVector.Builder(
            name = "New",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f,
        ).apply {
            path(fill = SolidColor(Color.Black)) {
                moveTo(12f, 1.5f)
                quadToRelative(-0.376f, 0f, -0.738f, 0.15f)
                quadToRelative(-0.363f, 0.149f, -0.662f, 0.424f)
                lineTo(8.65f, 4f)
                horizontalLineTo(6f)
                quadToRelative(-0.825f, 0f, -1.412f, 0.588f)
                arcTo(1.93f, 1.93f, 0f, isMoreThanHalf = false, isPositiveArc = false, 4f, 6f)
                verticalLineToRelative(2.65f)
                lineTo(2.074f, 10.6f)
                quadToRelative(-0.274f, 0.3f, -0.424f, 0.662f)
                reflectiveQuadTo(1.5f, 12f)
                curveToRelative(0f, 0.376f, 0.05f, 0.497f, 0.15f, 0.738f)
                quadToRelative(0.149f, 0.362f, 0.424f, 0.662f)
                lineTo(4f, 15.35f)
                verticalLineTo(18f)
                quadToRelative(0f, 0.825f, 0.588f, 1.412f)
                quadTo(5.175f, 20f, 6f, 20f)
                horizontalLineToRelative(2.65f)
                lineToRelative(1.95f, 1.926f)
                quadToRelative(0.3f, 0.274f, 0.662f, 0.424f)
                reflectiveQuadToRelative(0.738f, 0.15f)
                curveToRelative(0.376f, 0f, 0.497f, -0.05f, 0.738f, -0.15f)
                quadToRelative(0.363f, -0.149f, 0.662f, -0.424f)
                lineTo(15.35f, 20f)
                horizontalLineTo(18f)
                quadToRelative(0.825f, 0f, 1.412f, -0.588f)
                quadTo(20f, 18.825f, 20f, 18f)
                verticalLineToRelative(-2.65f)
                lineToRelative(1.926f, -1.95f)
                quadToRelative(0.274f, -0.3f, 0.424f, -0.662f)
                reflectiveQuadTo(22.5f, 12f)
                curveToRelative(0f, -0.376f, -0.05f, -0.497f, -0.15f, -0.738f)
                arcToRelative(
                    2.2f,
                    2.2f,
                    0f,
                    isMoreThanHalf = false,
                    isPositiveArc = false,
                    -0.424f,
                    -0.662f,
                )
                lineTo(20f, 8.65f)
                verticalLineTo(6f)
                quadToRelative(0f, -0.824f, -0.588f, -1.412f)
                arcTo(1.93f, 1.93f, 0f, isMoreThanHalf = false, isPositiveArc = false, 18f, 4f)
                horizontalLineToRelative(-2.65f)
                lineTo(13.4f, 2.074f)
                arcToRelative(
                    2.2f,
                    2.2f,
                    0f,
                    isMoreThanHalf = false,
                    isPositiveArc = false,
                    -0.662f,
                    -0.424f,
                )
                quadTo(12.376f, 1.5f, 12f, 1.5f)
                moveToRelative(0f, 2f)
                lineTo(14.5f, 6f)
                horizontalLineTo(18f)
                verticalLineToRelative(3.5f)
                lineToRelative(2.5f, 2.5f)
                lineToRelative(-2.5f, 2.5f)
                verticalLineTo(18f)
                horizontalLineToRelative(-3.5f)
                lineTo(12f, 20.5f)
                lineTo(9.5f, 18f)
                horizontalLineTo(6f)
                verticalLineToRelative(-3.5f)
                lineTo(3.5f, 12f)
                lineTo(6f, 9.5f)
                verticalLineTo(6f)
                horizontalLineToRelative(3.5f)
                close()
                moveTo(9.824f, 8.066f)
                quadToRelative(-0.47f, 0f, -0.8f, 0.342f)
                arcToRelative(
                    1.12f,
                    1.12f,
                    0f,
                    isMoreThanHalf = false,
                    isPositiveArc = false,
                    -0.325f,
                    0.81f,
                )
                verticalLineToRelative(5.776f)
                quadToRelative(0f, 0.384f, 0.272f, 0.662f)
                arcToRelative(
                    0.88f,
                    0.88f,
                    0f,
                    isMoreThanHalf = false,
                    isPositiveArc = false,
                    0.65f,
                    0.278f,
                )
                quadToRelative(0.384f, 0f, 0.656f, -0.272f)
                arcTo(0.9f, 0.9f, 0f, isMoreThanHalf = false, isPositiveArc = false, 10.55f, 15f)
                verticalLineToRelative(-3.754f)
                lineToRelative(2.918f, 4.219f)
                quadToRelative(0.122f, 0.192f, 0.351f, 0.33f)
                arcToRelative(
                    0.93f,
                    0.93f,
                    0f,
                    isMoreThanHalf = false,
                    isPositiveArc = false,
                    0.49f,
                    0.139f,
                )
                quadToRelative(0.418f, 0f, 0.706f, -0.3f)
                quadToRelative(0.288f, -0.297f, 0.287f, -0.724f)
                verticalLineTo(9.027f)
                arcToRelative(
                    0.9f,
                    0.9f,
                    0f,
                    isMoreThanHalf = false,
                    isPositiveArc = false,
                    -0.272f,
                    -0.656f,
                )
                arcToRelative(
                    0.9f,
                    0.9f,
                    0f,
                    isMoreThanHalf = false,
                    isPositiveArc = false,
                    -0.656f,
                    -0.277f,
                )
                arcToRelative(
                    0.88f,
                    0.88f,
                    0f,
                    isMoreThanHalf = false,
                    isPositiveArc = false,
                    -0.65f,
                    0.277f,
                )
                arcToRelative(
                    0.9f,
                    0.9f,
                    0f,
                    isMoreThanHalf = false,
                    isPositiveArc = false,
                    -0.272f,
                    0.656f,
                )
                verticalLineToRelative(3.584f)
                horizontalLineToRelative(-0.006f)
                lineToRelative(-2.64f, -3.974f)
                arcToRelative(
                    1.24f,
                    1.24f,
                    0f,
                    isMoreThanHalf = false,
                    isPositiveArc = false,
                    -0.405f,
                    -0.399f,
                )
                arcToRelative(
                    1.05f,
                    1.05f,
                    0f,
                    isMoreThanHalf = false,
                    isPositiveArc = false,
                    -0.576f,
                    -0.172f,
                )
            }
        }.build()

        return _New!!
    }

@Suppress("ObjectPropertyName")
private var _New: ImageVector? = null
