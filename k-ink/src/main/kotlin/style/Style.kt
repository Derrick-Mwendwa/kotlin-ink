package tech.derrickmwendwa.style

data class Style(
    val color: AnsiColor? = null,
    val background: AnsiBackground? = null,
    val styles: Set<TextStyle> = emptySet()
) {
    fun merge(other: Style?): Style {
        if (other == null) return this
        return Style(
            color = other.color ?: this.color,
            background = other.background ?: this.background,
            styles = this.styles + other.styles
        )
    }
}
