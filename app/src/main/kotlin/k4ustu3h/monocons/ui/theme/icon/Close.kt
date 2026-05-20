package k4ustu3h.monocons.ui.theme.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Monocons.Close: ImageVector
    get() {
        if (_Close != null) {
            return _Close!!
        }
        _Close = ImageVector.Builder(
            name = "Close",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f,
        ).apply {
            path(fill = SolidColor(Color.Black)) {
                moveToRelative(12f, 13.4f)
                lineToRelative(-4.9f, 4.9f)
                quadToRelative(-0.275f, 0.275f, -0.7f, 0.275f)
                reflectiveQuadToRelative(-0.7f, -0.275f)
                reflectiveQuadToRelative(-0.275f, -0.7f)
                reflectiveQuadToRelative(0.275f, -0.7f)
                lineToRelative(4.9f, -4.9f)
                lineToRelative(-4.9f, -4.9f)
                quadToRelative(-0.275f, -0.275f, -0.275f, -0.7f)
                reflectiveQuadToRelative(0.275f, -0.7f)
                reflectiveQuadToRelative(0.7f, -0.275f)
                reflectiveQuadToRelative(0.7f, 0.275f)
                lineToRelative(4.9f, 4.9f)
                lineToRelative(4.9f, -4.9f)
                quadToRelative(0.275f, -0.275f, 0.7f, -0.275f)
                reflectiveQuadToRelative(0.7f, 0.275f)
                reflectiveQuadToRelative(0.275f, 0.7f)
                reflectiveQuadToRelative(-0.275f, 0.7f)
                lineTo(13.4f, 12f)
                lineToRelative(4.9f, 4.9f)
                quadToRelative(0.275f, 0.275f, 0.275f, 0.7f)
                reflectiveQuadToRelative(-0.275f, 0.7f)
                reflectiveQuadToRelative(-0.7f, 0.275f)
                reflectiveQuadToRelative(-0.7f, -0.275f)
                close()
            }
        }.build()

        return _Close!!
    }

@Suppress("ObjectPropertyName")
private var _Close: ImageVector? = null
