package tech.derrickmwendwa

import tech.derrickmwendwa.style.AnsiBackground
import tech.derrickmwendwa.style.AnsiColor
import tech.derrickmwendwa.style.Style
import tech.derrickmwendwa.style.TextStyle

class KInkContext {
    private val segments = mutableListOf<Pair<String, Style?>>()
    private val lineStyles = mutableMapOf<Int, Style>()

    fun text(content: String, style: Style? = null) {
        segments.add(content to style)
    }

    fun withStyle(
        color: AnsiColor? = null,
        background: AnsiBackground? = null,
        styles: Set<TextStyle> = emptySet(),
        block: KInkContext.() -> Unit
    ) {
        val style = Style(color, background, styles)
        withStyle(style, block)
    }

    fun withStyle(style: Style, block: KInkContext.() -> Unit) {
        val subContext = KInkContext()
        subContext.block()
        // Flatten the sub-context segments into the main list, applying the parent style if needed
        subContext.getSegments().forEach { (txt, subStyle) ->
             val mergedStyle = mergeStyles(style, subStyle)
             segments.add(txt to mergedStyle)
        }
    }

    fun styleLine(row: Int, style: Style) {
        lineStyles[row] = style
    }

    internal fun getSegments() = segments.toList()
    internal fun getLineStyles() = lineStyles.toMap()

    private fun mergeStyles(parent: Style, child: Style?): Style {
        if (child == null) return parent
        // Child overrides parent if present
        return Style(
            color = child.color ?: parent.color,
            background = child.background ?: parent.background,
            styles = parent.styles + child.styles
        )
    }
}
