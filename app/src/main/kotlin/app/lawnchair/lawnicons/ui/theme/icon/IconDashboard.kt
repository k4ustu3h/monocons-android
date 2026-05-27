/*
 * Copyright 2026 Lawnchair Launcher
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package app.lawnchair.lawnicons.ui.theme.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val LawnIcons.IconDashboard: ImageVector
    get() {
        if (_Table1 != null) {
            return _Table1!!
        }
        _Table1 = ImageVector.Builder(
            name = "Table1",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 960f,
            viewportHeight = 960f,
        ).apply {
            path(fill = SolidColor(Color(0xFFE3E3E3))) {
                moveTo(200f, 840f)
                quadToRelative(-33f, 0f, -56.5f, -23.5f)
                reflectiveQuadTo(120f, 760f)
                verticalLineToRelative(-560f)
                quadToRelative(0f, -33f, 23.5f, -56.5f)
                reflectiveQuadTo(200f, 120f)
                horizontalLineToRelative(560f)
                quadToRelative(33f, 0f, 56.5f, 23.5f)
                reflectiveQuadTo(840f, 200f)
                verticalLineToRelative(560f)
                quadToRelative(0f, 33f, -23.5f, 56.5f)
                reflectiveQuadTo(760f, 840f)
                lineTo(200f, 840f)
                close()
                moveTo(440f, 600f)
                lineTo(200f, 600f)
                verticalLineToRelative(160f)
                horizontalLineToRelative(240f)
                verticalLineToRelative(-160f)
                close()
                moveTo(520f, 600f)
                verticalLineToRelative(160f)
                horizontalLineToRelative(240f)
                verticalLineToRelative(-160f)
                lineTo(520f, 600f)
                close()
                moveTo(440f, 520f)
                verticalLineToRelative(-160f)
                lineTo(200f, 360f)
                verticalLineToRelative(160f)
                horizontalLineToRelative(240f)
                close()
                moveTo(520f, 520f)
                horizontalLineToRelative(240f)
                verticalLineToRelative(-160f)
                lineTo(520f, 360f)
                verticalLineToRelative(160f)
                close()
                moveTo(200f, 280f)
                horizontalLineToRelative(560f)
                verticalLineToRelative(-80f)
                lineTo(200f, 200f)
                verticalLineToRelative(80f)
                close()
            }
        }.build()

        return _Table1!!
    }

@Suppress("ObjectPropertyName")
private var _Table1: ImageVector? = null
