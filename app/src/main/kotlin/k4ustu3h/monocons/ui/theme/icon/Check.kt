package k4ustu3h.monocons.ui.theme.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Monocons.Check: ImageVector
    get() {
        if (_Check != null) {
            return _Check!!
        }
        _Check = ImageVector.Builder(
            name = "Check",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f,
        ).apply {
            path(fill = SolidColor(Color.Black)) {
                moveToRelative(9.55f, 15.15f)
                lineToRelative(8.475f, -8.475f)
                quadToRelative(0.3f, -0.3f, 0.7f, -0.3f)
                reflectiveQuadToRelative(0.7f, 0.3f)
                reflectiveQuadToRelative(0.3f, 0.713f)
                reflectiveQuadToRelative(-0.3f, 0.712f)
                lineToRelative(-9.175f, 9.2f)
                quadToRelative(-0.3f, 0.3f, -0.7f, 0.3f)
                reflectiveQuadToRelative(-0.7f, -0.3f)
                lineTo(4.55f, 13f)
                quadToRelative(-0.3f, -0.3f, -0.288f, -0.712f)
                reflectiveQuadToRelative(0.313f, -0.713f)
                reflectiveQuadToRelative(0.712f, -0.3f)
                reflectiveQuadToRelative(0.713f, 0.3f)
                close()
            }
        }.build()

        return _Check!!
    }

@Suppress("ObjectPropertyName")
private var _Check: ImageVector? = null
