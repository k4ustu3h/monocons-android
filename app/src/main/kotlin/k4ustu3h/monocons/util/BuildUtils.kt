package k4ustu3h.monocons.util

import k4ustu3h.monocons.BuildConfig

val isIzzyBuild: Boolean
    get() = BuildConfig.FLAVOR == "izzy"
