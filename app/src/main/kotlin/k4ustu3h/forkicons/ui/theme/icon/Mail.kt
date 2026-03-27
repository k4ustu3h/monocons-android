package k4ustu3h.forkicons.ui.theme.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Forkicons.Mail: ImageVector
    get() {
        if (_Mail != null) {
            return _Mail!!
        }
        _Mail = ImageVector.Builder(
            name = "Mail",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f,
        ).apply {
            path(fill = SolidColor(Color.Black)) {
                moveTo(4f, 20f)
                quadToRelative(-0.825f, 0f, -1.412f, -0.587f)
                reflectiveQuadTo(2f, 18f)
                lineTo(2f, 6f)
                quadToRelative(0f, -0.825f, 0.588f, -1.412f)
                reflectiveQuadTo(4f, 4f)
                horizontalLineToRelative(16f)
                quadToRelative(0.825f, 0f, 1.413f, 0.588f)
                reflectiveQuadTo(22f, 6f)
                verticalLineToRelative(12f)
                quadToRelative(0f, 0.825f, -0.587f, 1.413f)
                reflectiveQuadTo(20f, 20f)
                close()
                moveTo(20f, 8f)
                lineToRelative(-7.475f, 4.675f)
                quadToRelative(-0.125f, 0.075f, -0.262f, 0.113f)
                reflectiveQuadToRelative(-0.263f, 0.037f)
                reflectiveQuadToRelative(-0.262f, -0.037f)
                reflectiveQuadToRelative(-0.263f, -0.113f)
                lineTo(4f, 8f)
                verticalLineToRelative(10f)
                horizontalLineToRelative(16f)
                close()
                moveTo(12f, 11f)
                lineTo(20f, 6f)
                lineTo(4f, 6f)
                close()
                moveTo(4f, 8f)
                verticalLineToRelative(0.25f)
                verticalLineToRelative(-1.475f)
                verticalLineToRelative(0.025f)
                lineTo(4f, 6f)
                verticalLineToRelative(0.8f)
                verticalLineToRelative(-0.012f)
                lineTo(4f, 8.25f)
                close()
                verticalLineToRelative(10f)
                close()
            }
        }.build()

        return _Mail!!
    }

@Suppress("ObjectPropertyName")
private var _Mail: ImageVector? = null
