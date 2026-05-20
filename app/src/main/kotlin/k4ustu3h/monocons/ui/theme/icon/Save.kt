package k4ustu3h.monocons.ui.theme.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Monocons.Save: ImageVector
    get() {
        if (_Save != null) {
            return _Save!!
        }
        _Save = ImageVector.Builder(
            name = "Save",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f,
        ).apply {
            path(fill = SolidColor(Color.Black)) {
                moveTo(5f, 21f)
                quadToRelative(-0.825f, 0f, -1.412f, -0.587f)
                reflectiveQuadTo(3f, 19f)
                lineTo(3f, 5f)
                quadToRelative(0f, -0.825f, 0.588f, -1.412f)
                reflectiveQuadTo(5f, 3f)
                horizontalLineToRelative(11.175f)
                quadToRelative(0.4f, 0f, 0.763f, 0.15f)
                reflectiveQuadToRelative(0.637f, 0.425f)
                lineToRelative(2.85f, 2.85f)
                quadToRelative(0.275f, 0.275f, 0.425f, 0.638f)
                reflectiveQuadToRelative(0.15f, 0.762f)
                lineTo(21f, 19f)
                quadToRelative(0f, 0.825f, -0.587f, 1.413f)
                reflectiveQuadTo(19f, 21f)
                close()
                moveTo(19f, 7.85f)
                lineTo(16.15f, 5f)
                lineTo(5f, 5f)
                verticalLineToRelative(14f)
                horizontalLineToRelative(14f)
                close()
                moveTo(14.125f, 17.125f)
                quadTo(15f, 16.25f, 15f, 15f)
                reflectiveQuadToRelative(-0.875f, -2.125f)
                reflectiveQuadTo(12f, 12f)
                reflectiveQuadToRelative(-2.125f, 0.875f)
                reflectiveQuadTo(9f, 15f)
                reflectiveQuadToRelative(0.875f, 2.125f)
                reflectiveQuadTo(12f, 18f)
                reflectiveQuadToRelative(2.125f, -0.875f)
                moveTo(7f, 10f)
                horizontalLineToRelative(7f)
                quadToRelative(0.425f, 0f, 0.713f, -0.287f)
                reflectiveQuadTo(15f, 9f)
                lineTo(15f, 7f)
                quadToRelative(0f, -0.425f, -0.287f, -0.712f)
                reflectiveQuadTo(14f, 6f)
                lineTo(7f, 6f)
                quadToRelative(-0.425f, 0f, -0.712f, 0.288f)
                reflectiveQuadTo(6f, 7f)
                verticalLineToRelative(2f)
                quadToRelative(0f, 0.425f, 0.288f, 0.713f)
                reflectiveQuadTo(7f, 10f)
                moveTo(5f, 7.85f)
                lineTo(5f, 19f)
                lineTo(5f, 5f)
                close()
            }
        }.build()

        return _Save!!
    }

@Suppress("ObjectPropertyName")
private var _Save: ImageVector? = null
