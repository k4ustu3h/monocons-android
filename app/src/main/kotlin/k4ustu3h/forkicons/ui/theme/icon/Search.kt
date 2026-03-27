package k4ustu3h.forkicons.ui.theme.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Forkicons.Search: ImageVector
    get() {
        if (_Search != null) {
            return _Search!!
        }
        _Search = ImageVector.Builder(
            name = "Search",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f,
        ).apply {
            path(fill = SolidColor(Color.Black)) {
                moveTo(9.5f, 16f)
                quadToRelative(-2.725f, 0f, -4.612f, -1.887f)
                reflectiveQuadTo(3f, 9.5f)
                reflectiveQuadToRelative(1.888f, -4.612f)
                reflectiveQuadTo(9.5f, 3f)
                reflectiveQuadToRelative(4.613f, 1.888f)
                reflectiveQuadTo(16f, 9.5f)
                quadToRelative(0f, 1.1f, -0.35f, 2.075f)
                reflectiveQuadTo(14.7f, 13.3f)
                lineToRelative(5.6f, 5.6f)
                quadToRelative(0.275f, 0.275f, 0.275f, 0.7f)
                reflectiveQuadToRelative(-0.275f, 0.7f)
                reflectiveQuadToRelative(-0.7f, 0.275f)
                reflectiveQuadToRelative(-0.7f, -0.275f)
                lineToRelative(-5.6f, -5.6f)
                quadToRelative(-0.75f, 0.6f, -1.725f, 0.95f)
                reflectiveQuadTo(9.5f, 16f)
                moveToRelative(0f, -2f)
                quadToRelative(1.875f, 0f, 3.188f, -1.312f)
                reflectiveQuadTo(14f, 9.5f)
                reflectiveQuadToRelative(-1.312f, -3.187f)
                reflectiveQuadTo(9.5f, 5f)
                reflectiveQuadTo(6.313f, 6.313f)
                reflectiveQuadTo(5f, 9.5f)
                reflectiveQuadToRelative(1.313f, 3.188f)
                reflectiveQuadTo(9.5f, 14f)
            }
        }.build()

        return _Search!!
    }

@Suppress("ObjectPropertyName")
private var _Search: ImageVector? = null
