package k4ustu3h.monocons.ui.theme.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Monocons.Back: ImageVector
    get() {
        if (_Back != null) {
            return _Back!!
        }
        _Back = ImageVector.Builder(
            name = "Back",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f,
        ).apply {
            path(fill = SolidColor(Color.Black)) {
                moveToRelative(7.825f, 13f)
                lineToRelative(4.9f, 4.9f)
                quadToRelative(0.3f, 0.3f, 0.288f, 0.7f)
                reflectiveQuadToRelative(-0.313f, 0.7f)
                quadToRelative(-0.3f, 0.275f, -0.7f, 0.287f)
                reflectiveQuadToRelative(-0.7f, -0.287f)
                lineToRelative(-6.6f, -6.6f)
                quadToRelative(-0.15f, -0.15f, -0.212f, -0.325f)
                reflectiveQuadTo(4.425f, 12f)
                reflectiveQuadToRelative(0.063f, -0.375f)
                reflectiveQuadToRelative(0.212f, -0.325f)
                lineToRelative(6.6f, -6.6f)
                quadToRelative(0.275f, -0.275f, 0.688f, -0.275f)
                reflectiveQuadToRelative(0.712f, 0.275f)
                quadToRelative(0.3f, 0.3f, 0.3f, 0.712f)
                reflectiveQuadToRelative(-0.3f, 0.713f)
                lineTo(7.825f, 11f)
                horizontalLineTo(19f)
                quadToRelative(0.425f, 0f, 0.713f, 0.288f)
                reflectiveQuadTo(20f, 12f)
                reflectiveQuadToRelative(-0.287f, 0.713f)
                reflectiveQuadTo(19f, 13f)
                close()
            }
        }.build()

        return _Back!!
    }

@Suppress("ObjectPropertyName")
private var _Back: ImageVector? = null
