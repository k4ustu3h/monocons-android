package k4ustu3h.monocons.ui.util

import android.annotation.SuppressLint
import android.content.Context
import k4ustu3h.monocons.R
import k4ustu3h.monocons.util.isIzzyBuild

@SuppressLint("DiscouragedApi")
object AvatarLoader {
    fun getAvatarModel(context: Context, id: Long, remoteUrl: String): Any {
        if (!isIzzyBuild) {
            val safeName = "avatar_u$id"
            val resId = context.resources.getIdentifier(safeName, "drawable", context.packageName)

            return if (resId != 0) resId else R.drawable.ic_launcher_foreground
        }
        return remoteUrl
    }
}
