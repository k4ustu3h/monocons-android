package k4ustu3h.forkicons.ui.theme.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Forkicons.Share: ImageVector
    get() {
        if (_Share != null) {
            return _Share!!
        }
        _Share = ImageVector.Builder(
            name = "Share",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f,
        ).apply {
            path(fill = SolidColor(Color.Black)) {
                moveTo(17f, 22f)
                quadToRelative(-1.25f, 0f, -2.125f, -0.875f)
                reflectiveQuadTo(14f, 19f)
                quadToRelative(0f, -0.15f, 0.075f, -0.7f)
                lineTo(7.05f, 14.2f)
                quadToRelative(-0.4f, 0.375f, -0.925f, 0.588f)
                reflectiveQuadTo(5f, 15f)
                quadToRelative(-1.25f, 0f, -2.125f, -0.875f)
                reflectiveQuadTo(2f, 12f)
                reflectiveQuadToRelative(0.875f, -2.125f)
                reflectiveQuadTo(5f, 9f)
                quadToRelative(0.6f, 0f, 1.125f, 0.213f)
                reflectiveQuadToRelative(0.925f, 0.587f)
                lineToRelative(7.025f, -4.1f)
                quadToRelative(-0.05f, -0.175f, -0.062f, -0.337f)
                reflectiveQuadTo(14f, 5f)
                quadToRelative(0f, -1.25f, 0.875f, -2.125f)
                reflectiveQuadTo(17f, 2f)
                reflectiveQuadToRelative(2.125f, 0.875f)
                reflectiveQuadTo(20f, 5f)
                reflectiveQuadToRelative(-0.875f, 2.125f)
                reflectiveQuadTo(17f, 8f)
                quadToRelative(-0.6f, 0f, -1.125f, -0.212f)
                reflectiveQuadTo(14.95f, 7.2f)
                lineToRelative(-7.025f, 4.1f)
                quadToRelative(0.05f, 0.175f, 0.062f, 0.338f)
                reflectiveQuadTo(8f, 12f)
                reflectiveQuadToRelative(-0.013f, 0.363f)
                reflectiveQuadToRelative(-0.062f, 0.337f)
                lineToRelative(7.025f, 4.1f)
                quadToRelative(0.4f, -0.375f, 0.925f, -0.587f)
                reflectiveQuadTo(17f, 16f)
                quadToRelative(1.25f, 0f, 2.125f, 0.875f)
                reflectiveQuadTo(20f, 19f)
                reflectiveQuadToRelative(-0.875f, 2.125f)
                reflectiveQuadTo(17f, 22f)
                moveToRelative(0f, -2f)
                quadToRelative(0.425f, 0f, 0.713f, -0.287f)
                reflectiveQuadTo(18f, 19f)
                reflectiveQuadToRelative(-0.287f, -0.712f)
                reflectiveQuadTo(17f, 18f)
                reflectiveQuadToRelative(-0.712f, 0.288f)
                reflectiveQuadTo(16f, 19f)
                reflectiveQuadToRelative(0.288f, 0.713f)
                reflectiveQuadTo(17f, 20f)
                moveTo(5f, 13f)
                quadToRelative(0.425f, 0f, 0.713f, -0.287f)
                reflectiveQuadTo(6f, 12f)
                reflectiveQuadToRelative(-0.287f, -0.712f)
                reflectiveQuadTo(5f, 11f)
                reflectiveQuadToRelative(-0.712f, 0.288f)
                reflectiveQuadTo(4f, 12f)
                reflectiveQuadToRelative(0.287f, 0.713f)
                reflectiveQuadTo(5f, 13f)
                moveToRelative(12.712f, -7.287f)
                quadTo(18f, 5.425f, 18f, 5f)
                reflectiveQuadToRelative(-0.288f, -0.712f)
                reflectiveQuadTo(17f, 4f)
                reflectiveQuadToRelative(-0.713f, 0.287f)
                reflectiveQuadTo(16f, 5f)
                reflectiveQuadToRelative(0.287f, 0.713f)
                reflectiveQuadTo(17f, 6f)
                reflectiveQuadToRelative(0.712f, -0.287f)
            }
        }.build()

        return _Share!!
    }

@Suppress("ObjectPropertyName")
private var _Share: ImageVector? = null
