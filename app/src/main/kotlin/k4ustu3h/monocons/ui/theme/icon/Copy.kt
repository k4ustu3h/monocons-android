package k4ustu3h.monocons.ui.theme.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Monocons.Copy: ImageVector
    get() {
        if (_Copy != null) {
            return _Copy!!
        }
        _Copy = ImageVector.Builder(
            name = "Copy",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f,
        ).apply {
            path(fill = SolidColor(Color.Black)) {
                moveTo(9f, 18f)
                quadToRelative(-0.825f, 0f, -1.412f, -0.587f)
                reflectiveQuadTo(7f, 16f)
                lineTo(7f, 4f)
                quadToRelative(0f, -0.825f, 0.588f, -1.412f)
                reflectiveQuadTo(9f, 2f)
                horizontalLineToRelative(9f)
                quadToRelative(0.825f, 0f, 1.413f, 0.588f)
                reflectiveQuadTo(20f, 4f)
                verticalLineToRelative(12f)
                quadToRelative(0f, 0.825f, -0.587f, 1.413f)
                reflectiveQuadTo(18f, 18f)
                close()
                moveTo(9f, 16f)
                horizontalLineToRelative(9f)
                lineTo(18f, 4f)
                lineTo(9f, 4f)
                close()
                moveTo(5f, 22f)
                quadToRelative(-0.825f, 0f, -1.412f, -0.587f)
                reflectiveQuadTo(3f, 20f)
                lineTo(3f, 7f)
                quadToRelative(0f, -0.425f, 0.288f, -0.712f)
                reflectiveQuadTo(4f, 6f)
                reflectiveQuadToRelative(0.713f, 0.288f)
                reflectiveQuadTo(5f, 7f)
                verticalLineToRelative(13f)
                horizontalLineToRelative(10f)
                quadToRelative(0.425f, 0f, 0.713f, 0.288f)
                reflectiveQuadTo(16f, 21f)
                reflectiveQuadToRelative(-0.287f, 0.713f)
                reflectiveQuadTo(15f, 22f)
                close()
                moveTo(9f, 16f)
                lineTo(9f, 4f)
                close()
            }
        }.build()

        return _Copy!!
    }

@Suppress("ObjectPropertyName")
private var _Copy: ImageVector? = null
