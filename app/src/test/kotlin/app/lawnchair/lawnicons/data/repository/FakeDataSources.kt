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

package app.lawnchair.lawnicons.data.repository

import app.lawnchair.lawnicons.data.model.IconInfo
import app.lawnchair.lawnicons.data.model.LabelAndComponent

class FakeIconDataSource(
    private val icons: List<IconInfo> = defaultIcons,
) : IconDataSource {
    override fun getIconInfo(): List<IconInfo> = icons

    companion object {
        val defaultIcons = listOf(
            IconInfo(
                drawableName = "zalpha_notes",
                componentNames = listOf(
                    LabelAndComponent("Zalpha", "com.example.notes/.MainActivity"),
                ),
                drawableId = 10,
            ),
            IconInfo(
                drawableName = "my_alpha_tools",
                componentNames = listOf(
                    LabelAndComponent("My Alpha", "com.example.tools/.MainActivity"),
                ),
                drawableId = 11,
            ),
            IconInfo(
                drawableName = "alpha_mail",
                componentNames = listOf(
                    LabelAndComponent("Alpha", "com.example.mail/.MainActivity"),
                ),
                drawableId = 12,
            ),
            IconInfo(
                drawableName = "alpha_settings",
                componentNames = listOf(
                    LabelAndComponent("Alpha", "com.example.settings/.MainActivity"),
                ),
                drawableId = 13,
            ),
        )
    }
}
