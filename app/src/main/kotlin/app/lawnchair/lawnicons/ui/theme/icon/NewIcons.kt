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
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val LawnIcons.NewIcons: ImageVector
    get() {
        if (_New != null) {
            return _New!!
        }
        _New = ImageVector.Builder(
            name = "New",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f,
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                pathFillType = PathFillType.EvenOdd,
            ) {
                moveTo(14.67f, 2.092f)
                arcToRelative(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = true, 1.292f, 0.419f)
                lineToRelative(1.378f, 2.456f)
                lineToRelative(2.914f, 0.484f)
                arcToRelative(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0.825f, 1.134f)
                lineToRelative(-0.406f, 2.718f)
                lineToRelative(2.026f, 1.982f)
                arcToRelative(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0f, 1.43f)
                lineToRelative(-2.026f, 1.982f)
                lineToRelative(0.406f, 2.717f)
                arcToRelative(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = true, -0.825f, 1.135f)
                lineToRelative(-2.914f, 0.483f)
                lineToRelative(-1.378f, 2.457f)
                arcToRelative(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = true, -1.292f, 0.418f)
                lineTo(12f, 20.672f)
                lineToRelative(-2.67f, 1.235f)
                arcToRelative(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = true, -1.292f, -0.418f)
                lineTo(6.66f, 19.032f)
                lineToRelative(-2.914f, -0.483f)
                arcToRelative(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = true, -0.825f, -1.135f)
                lineToRelative(0.406f, -2.717f)
                lineToRelative(-2.026f, -1.982f)
                arcToRelative(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0f, -1.43f)
                lineToRelative(2.026f, -1.982f)
                lineToRelative(-0.406f, -2.718f)
                arcToRelative(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0.825f, -1.134f)
                lineToRelative(2.914f, -0.484f)
                lineToRelative(1.378f, -2.456f)
                arcToRelative(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = true, 1.292f, -0.419f)
                lineTo(12f, 3.328f)
                lineToRelative(2.67f, -1.236f)
                close()
                moveTo(14.67f, 4.296f)
                lineTo(12.42f, 5.337f)
                arcToRelative(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = true, -0.84f, 0f)
                lineTo(9.33f, 4.296f)
                lineTo(8.17f, 6.364f)
                arcToRelative(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = true, -0.709f, 0.498f)
                lineToRelative(-2.417f, 0.401f)
                lineToRelative(0.337f, 2.25f)
                arcToRelative(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = true, -0.29f, 0.862f)
                lineTo(3.43f, 12f)
                lineToRelative(1.66f, 1.624f)
                arcToRelative(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0.29f, 0.863f)
                lineToRelative(-0.337f, 2.25f)
                lineToRelative(2.417f, 0.401f)
                arcToRelative(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0.709f, 0.498f)
                lineToRelative(1.16f, 2.068f)
                lineToRelative(2.25f, -1.041f)
                arcToRelative(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0.84f, 0f)
                lineToRelative(2.25f, 1.04f)
                lineToRelative(1.16f, -2.067f)
                arcToRelative(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0.708f, -0.498f)
                lineToRelative(2.418f, -0.401f)
                lineToRelative(-0.337f, -2.25f)
                arcToRelative(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0.29f, -0.863f)
                lineTo(20.57f, 12f)
                lineToRelative(-1.66f, -1.625f)
                arcToRelative(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = true, -0.29f, -0.862f)
                lineToRelative(0.337f, -2.25f)
                lineToRelative(-2.418f, -0.401f)
                arcToRelative(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = true, -0.708f, -0.498f)
                lineToRelative(-1.16f, -2.068f)
                close()
            }
            path(fill = SolidColor(Color.Black)) {
                moveTo(9f, 14f)
                verticalLineToRelative(-4f)
                arcToRelative(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = true, 1.707f, -0.707f)
                lineTo(13f, 11.586f)
                verticalLineTo(10f)
                arcToRelative(1f, 1f, 0f, isMoreThanHalf = true, isPositiveArc = true, 2f, 0f)
                verticalLineToRelative(4f)
                arcToRelative(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = true, -1.707f, 0.707f)
                lineTo(11f, 12.414f)
                verticalLineTo(14f)
                arcToRelative(1f, 1f, 0f, isMoreThanHalf = true, isPositiveArc = true, -2f, 0f)
                close()
            }
        }.build()

        return _New!!
    }

@Suppress("ObjectPropertyName")
private var _New: ImageVector? = null
