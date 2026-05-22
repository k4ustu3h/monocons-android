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
            name = "Monocons",
            defaultWidth = 108.dp,
            defaultHeight = 108.dp,
            viewportWidth = 108f,
            viewportHeight = 108f,
        ).apply {
            path(fill = SolidColor(Color(0xFFFFD600))) {
                moveTo(108f, 0f)
                moveToRelative(-81f, 0f)
                arcToRelative(81f, 81f, 0f, isMoreThanHalf = true, isPositiveArc = true, 162f, 0f)
                arcToRelative(81f, 81f, 0f, isMoreThanHalf = true, isPositiveArc = true, -162f, 0f)
            }
            path(fill = SolidColor(Color(0xFFD50000))) {
                moveTo(108f, 108f)
                moveToRelative(-81f, 0f)
                arcToRelative(81f, 81f, 0f, isMoreThanHalf = true, isPositiveArc = true, 162f, 0f)
                arcToRelative(81f, 81f, 0f, isMoreThanHalf = true, isPositiveArc = true, -162f, 0f)
            }
            path(fill = SolidColor(Color(0xFF00C853))) {
                moveTo(0f, 108f)
                moveToRelative(-81f, 0f)
                arcToRelative(81f, 81f, 0f, isMoreThanHalf = true, isPositiveArc = true, 162f, 0f)
                arcToRelative(81f, 81f, 0f, isMoreThanHalf = true, isPositiveArc = true, -162f, 0f)
            }
            path(fill = SolidColor(Color(0xFF2962FF))) {
                moveTo(0f, 0f)
                moveToRelative(-81f, 0f)
                arcToRelative(81f, 81f, 0f, isMoreThanHalf = true, isPositiveArc = true, 162f, 0f)
                arcToRelative(81f, 81f, 0f, isMoreThanHalf = true, isPositiveArc = true, -162f, 0f)
            }
            path(fill = SolidColor(Color.White)) {
                moveToRelative(28.99f, 48.16f)
                lineToRelative(8.66f, -15.62f)
                quadToRelative(0.39f, -0.72f, 0.98f, -1.05f)
                quadToRelative(0.59f, -0.33f, 1.31f, -0.33f)
                quadToRelative(0.72f, 0f, 1.31f, 0.33f)
                quadToRelative(0.59f, 0.33f, 0.98f, 1.05f)
                lineToRelative(8.66f, 15.62f)
                quadToRelative(0.33f, 0.66f, 0.3f, 1.31f)
                quadToRelative(-0.03f, 0.66f, -0.36f, 1.25f)
                quadToRelative(-0.33f, 0.59f, -0.92f, 0.95f)
                quadToRelative(-0.59f, 0.36f, -1.31f, 0.36f)
                horizontalLineToRelative(-17.32f)
                quadToRelative(-0.72f, 0f, -1.31f, -0.36f)
                quadToRelative(-0.59f, -0.36f, -0.92f, -0.95f)
                quadToRelative(-0.33f, -0.59f, -0.36f, -1.25f)
                quadToRelative(-0.03f, -0.66f, 0.3f, -1.31f)
                close()
                moveTo(32.53f, 75.19f)
                quadToRelative(-3.08f, -3.08f, -3.08f, -7.41f)
                quadToRelative(0f, -4.4f, 3.08f, -7.45f)
                quadToRelative(3.08f, -3.05f, 7.41f, -3.05f)
                quadToRelative(4.33f, 0f, 7.41f, 3.08f)
                quadToRelative(3.08f, 3.08f, 3.08f, 7.41f)
                quadToRelative(0f, 4.33f, -3.08f, 7.41f)
                quadToRelative(-3.08f, 3.08f, -7.41f, 3.08f)
                quadToRelative(-4.33f, 0f, -7.41f, -3.08f)
                close()
                moveTo(43.65f, 71.49f)
                quadToRelative(1.54f, -1.54f, 1.54f, -3.71f)
                quadToRelative(0f, -2.17f, -1.54f, -3.71f)
                quadToRelative(-1.54f, -1.54f, -3.71f, -1.54f)
                reflectiveQuadToRelative(-3.71f, 1.54f)
                quadToRelative(-1.54f, 1.54f, -1.54f, 3.71f)
                quadToRelative(0f, 2.17f, 1.54f, 3.71f)
                quadToRelative(1.54f, 1.54f, 3.71f, 1.54f)
                reflectiveQuadToRelative(3.71f, -1.54f)
                close()
                moveTo(35.74f, 46.78f)
                horizontalLineToRelative(8.4f)
                lineToRelative(-4.2f, -7.55f)
                close()
                moveTo(55.69f, 75.65f)
                verticalLineToRelative(-15.75f)
                quadToRelative(0f, -1.12f, 0.75f, -1.87f)
                quadToRelative(0.75f, -0.75f, 1.87f, -0.75f)
                horizontalLineToRelative(15.75f)
                quadToRelative(1.12f, 0f, 1.87f, 0.75f)
                quadToRelative(0.75f, 0.75f, 0.75f, 1.87f)
                verticalLineToRelative(15.75f)
                quadToRelative(0f, 1.12f, -0.75f, 1.87f)
                quadToRelative(-0.75f, 0.75f, -1.87f, 0.75f)
                horizontalLineToRelative(-15.75f)
                quadToRelative(-1.12f, 0f, -1.87f, -0.75f)
                quadToRelative(-0.75f, -0.75f, -0.75f, -1.87f)
                close()
                moveTo(60.94f, 73.03f)
                horizontalLineToRelative(10.5f)
                verticalLineToRelative(-10.5f)
                horizontalLineToRelative(-10.5f)
                close()
                moveTo(64.48f, 50.59f)
                lineTo(61.01f, 47.64f)
                quadToRelative(-4.53f, -3.81f, -6.23f, -6f)
                quadToRelative(-1.71f, -2.2f, -1.71f, -4.89f)
                quadToRelative(0f, -2.95f, 2.07f, -4.99f)
                reflectiveQuadToRelative(5.15f, -2.03f)
                quadToRelative(1.77f, 0f, 3.31f, 0.82f)
                quadToRelative(1.54f, 0.82f, 2.59f, 2.26f)
                quadToRelative(1.05f, -1.44f, 2.59f, -2.26f)
                reflectiveQuadToRelative(3.31f, -0.82f)
                quadToRelative(3.08f, 0f, 5.15f, 2.03f)
                quadToRelative(2.07f, 2.03f, 2.07f, 4.99f)
                quadToRelative(0f, 2.69f, -1.71f, 4.89f)
                quadToRelative(-1.71f, 2.2f, -6.23f, 6f)
                lineToRelative(-3.48f, 2.95f)
                quadToRelative(-0.72f, 0.66f, -1.71f, 0.66f)
                quadToRelative(-0.98f, 0f, -1.71f, -0.66f)
                close()
                moveTo(66.19f, 45.14f)
                quadToRelative(4.72f, -3.94f, 6.3f, -5.58f)
                quadToRelative(1.57f, -1.64f, 1.57f, -2.69f)
                quadToRelative(0f, -0.85f, -0.49f, -1.38f)
                quadToRelative(-0.49f, -0.52f, -1.35f, -0.52f)
                quadToRelative(-0.66f, 0f, -1.28f, 0.36f)
                quadToRelative(-0.62f, 0.36f, -1.54f, 1.28f)
                lineToRelative(-3.22f, 3.08f)
                lineToRelative(-3.22f, -3.08f)
                quadToRelative(-0.92f, -0.92f, -1.54f, -1.28f)
                quadToRelative(-0.62f, -0.36f, -1.28f, -0.36f)
                quadToRelative(-0.85f, 0f, -1.35f, 0.52f)
                quadToRelative(-0.49f, 0.52f, -0.49f, 1.38f)
                quadToRelative(0f, 1.05f, 1.57f, 2.69f)
                quadToRelative(1.57f, 1.64f, 6.3f, 5.58f)
                close()
            }
        }.build()

        return _AppIcon!!
    }

@Suppress("ObjectPropertyName")
private var _AppIcon: ImageVector? = null
