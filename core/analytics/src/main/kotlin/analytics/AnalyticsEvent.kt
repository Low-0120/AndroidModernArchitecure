package analytics
data class AnalyticsEvent(
    val type: String,
    val extras: List<Param> = emptyList()
) {
    class Type{
        companion object{
            const val SCREEN_VIEW = "screen_view"
        }
    }

    data class Param(val key: String, val value: String)

    class ParamKeys{
        companion object{
            const val SCREEN_VIEW = "screen_view"
        }
    }
}