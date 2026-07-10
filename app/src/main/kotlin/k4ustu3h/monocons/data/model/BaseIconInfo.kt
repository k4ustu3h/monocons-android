/*
 * Copyright 2025 Lawnchair Launcher
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

package k4ustu3h.monocons.data.model

import kotlinx.serialization.Serializable

interface BaseIconInfo {
    val componentNames: List<LabelAndComponent>

    /**
     * The user-facing label associated with the icon, derived from the first available
     * [LabelAndComponent] object.
     */
    val label: String
        get() = componentNames.firstOrNull()?.label ?: ""
}

fun BaseIconInfo.getFirstLabelAndComponent(): LabelAndComponent {
    val firstLabel = componentNames.firstOrNull()?.label ?: ""
    val firstComponent = componentNames.firstOrNull()?.component ?: Component()
    return LabelAndComponent(firstLabel, firstComponent)
}

/**
 * Data class representing a label and component name pair.
 *
 * @property label The user-facing label associated with the component.
 * @property component The name of the component, typically a fully qualified class name.
 */
@Serializable
data class LabelAndComponent(
    val label: String,
    val component: Component,
) {
    constructor(
        label: String,
        componentName: String,
    ) : this(label, Component.unflattenFromString(componentName)!!)
}

@Serializable
data class Component(
    val packageName: String,
    val className: String,
) : Comparable<Component> {
    constructor() : this("", "")

    fun flattenToString() = "$packageName/$className"

    override fun toString(): String {
        return "ComponentInfo{$packageName/$className}"
    }

    override fun equals(other: Any?) =
        other is Component && packageName == other.packageName && className == other.className

    override fun hashCode() = packageName.hashCode() + className.hashCode()

    override fun compareTo(other: Component) =
        compareValuesBy(this, other, Component::packageName, Component::className)

    companion object {
        fun unflattenFromString(str: String): Component? {
            val sep = str.indexOf('/')
            if (sep < 0 || (sep + 1) >= str.length) return null

            val pkg = str.substring(0, sep)
            val rawCls = str.substring(sep + 1)
            val cls = if (rawCls.startsWith('.')) "$pkg$rawCls" else rawCls

            return Component(pkg, cls)
        }
    }
}
