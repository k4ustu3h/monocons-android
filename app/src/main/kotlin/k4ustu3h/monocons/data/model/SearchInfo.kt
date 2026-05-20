package k4ustu3h.monocons.data.model

data class SearchInfo(
    val iconInfo: IconInfo,
    val indexOfMatch: Int,
    val matchAtWordStart: Boolean,
)

enum class SearchMode {
    LABEL,
    COMPONENT,
    DRAWABLE,
}
