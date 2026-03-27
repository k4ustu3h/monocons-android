package k4ustu3h.forkicons.ui.theme.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Forkicons.About: ImageVector
    get() {
        if (_AboutIcon != null) {
            return _AboutIcon!!
        }
        _AboutIcon = ImageVector.Builder(
            name = "AboutIcon",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f,
        ).apply {
            path(fill = SolidColor(Color.Black)) {
                moveTo(1f, 17.2f)
                quadToRelative(0f, -0.85f, 0.438f, -1.562f)
                reflectiveQuadTo(2.6f, 14.55f)
                quadToRelative(1.55f, -0.775f, 3.15f, -1.163f)
                reflectiveQuadTo(9f, 13f)
                reflectiveQuadToRelative(3.25f, 0.388f)
                reflectiveQuadToRelative(3.15f, 1.162f)
                quadToRelative(0.725f, 0.375f, 1.163f, 1.087f)
                reflectiveQuadTo(17f, 17.2f)
                verticalLineToRelative(0.8f)
                quadToRelative(0f, 0.825f, -0.587f, 1.413f)
                reflectiveQuadTo(15f, 20f)
                lineTo(3f, 20f)
                quadToRelative(-0.825f, 0f, -1.412f, -0.587f)
                reflectiveQuadTo(1f, 18f)
                close()
                moveTo(21f, 20f)
                horizontalLineToRelative(-2.55f)
                quadToRelative(0.275f, -0.45f, 0.413f, -0.962f)
                reflectiveQuadTo(19f, 18f)
                verticalLineToRelative(-1f)
                quadToRelative(0f, -1.1f, -0.612f, -2.112f)
                reflectiveQuadTo(16.65f, 13.15f)
                quadToRelative(1.275f, 0.15f, 2.4f, 0.512f)
                reflectiveQuadToRelative(2.1f, 0.888f)
                quadToRelative(0.9f, 0.5f, 1.375f, 1.112f)
                reflectiveQuadTo(23f, 17f)
                verticalLineToRelative(1f)
                quadToRelative(0f, 0.825f, -0.587f, 1.412f)
                reflectiveQuadTo(21f, 20f)
                moveTo(6.175f, 10.825f)
                quadTo(5f, 9.65f, 5f, 8f)
                reflectiveQuadToRelative(1.175f, -2.825f)
                reflectiveQuadTo(9f, 4f)
                reflectiveQuadToRelative(2.825f, 1.175f)
                reflectiveQuadTo(13f, 8f)
                reflectiveQuadToRelative(-1.175f, 2.825f)
                reflectiveQuadTo(9f, 12f)
                reflectiveQuadToRelative(-2.825f, -1.175f)
                moveToRelative(11.65f, 0f)
                quadTo(16.65f, 12f, 15f, 12f)
                quadToRelative(-0.275f, 0f, -0.7f, -0.062f)
                reflectiveQuadToRelative(-0.7f, -0.138f)
                quadToRelative(0.675f, -0.8f, 1.038f, -1.775f)
                reflectiveQuadTo(15f, 8f)
                reflectiveQuadToRelative(-0.362f, -2.025f)
                reflectiveQuadTo(13.6f, 4.2f)
                quadToRelative(0.35f, -0.125f, 0.7f, -0.163f)
                reflectiveQuadTo(15f, 4f)
                quadToRelative(1.65f, 0f, 2.825f, 1.175f)
                reflectiveQuadTo(19f, 8f)
                reflectiveQuadToRelative(-1.175f, 2.825f)
                moveTo(3f, 18f)
                horizontalLineToRelative(12f)
                verticalLineToRelative(-0.8f)
                quadToRelative(0f, -0.275f, -0.137f, -0.5f)
                reflectiveQuadToRelative(-0.363f, -0.35f)
                quadToRelative(-1.35f, -0.675f, -2.725f, -1.013f)
                reflectiveQuadTo(9f, 15f)
                reflectiveQuadToRelative(-2.775f, 0.337f)
                reflectiveQuadTo(3.5f, 16.35f)
                quadToRelative(-0.225f, 0.125f, -0.362f, 0.35f)
                reflectiveQuadTo(3f, 17.2f)
                close()
                moveTo(10.413f, 9.413f)
                quadTo(11f, 8.825f, 11f, 8f)
                reflectiveQuadToRelative(-0.587f, -1.412f)
                reflectiveQuadTo(9f, 6f)
                reflectiveQuadToRelative(-1.412f, 0.588f)
                reflectiveQuadTo(7f, 8f)
                reflectiveQuadToRelative(0.588f, 1.413f)
                reflectiveQuadTo(9f, 10f)
                reflectiveQuadToRelative(1.413f, -0.587f)
            }
        }.build()

        return _AboutIcon!!
    }

@Suppress("ObjectPropertyName")
private var _AboutIcon: ImageVector? = null
