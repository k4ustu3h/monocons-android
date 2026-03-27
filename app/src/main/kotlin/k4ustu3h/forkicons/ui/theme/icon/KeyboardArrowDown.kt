package k4ustu3h.forkicons.ui.theme.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Forkicons.KeyboardArrowDown: ImageVector
    get() {
        if (_KeyboardArrowDown != null) {
            return _KeyboardArrowDown!!
        }
        _KeyboardArrowDown = ImageVector.Builder(
            name = "KeyboardArrowDown",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f,
        ).apply {
            path(fill = SolidColor(Color.Black)) {
                moveTo(11.625f, 14.912f)
                quadToRelative(-0.175f, -0.062f, -0.325f, -0.212f)
                lineToRelative(-4.6f, -4.6f)
                quadToRelative(-0.275f, -0.275f, -0.275f, -0.7f)
                reflectiveQuadToRelative(0.275f, -0.7f)
                reflectiveQuadToRelative(0.7f, -0.275f)
                reflectiveQuadToRelative(0.7f, 0.275f)
                lineToRelative(3.9f, 3.9f)
                lineToRelative(3.9f, -3.9f)
                quadToRelative(0.275f, -0.275f, 0.7f, -0.275f)
                reflectiveQuadToRelative(0.7f, 0.275f)
                reflectiveQuadToRelative(0.275f, 0.7f)
                reflectiveQuadToRelative(-0.275f, 0.7f)
                lineToRelative(-4.6f, 4.6f)
                quadToRelative(-0.15f, 0.15f, -0.325f, 0.212f)
                reflectiveQuadToRelative(-0.375f, 0.063f)
                reflectiveQuadToRelative(-0.375f, -0.063f)
            }
        }.build()

        return _KeyboardArrowDown!!
    }

@Suppress("ObjectPropertyName")
private var _KeyboardArrowDown: ImageVector? = null
