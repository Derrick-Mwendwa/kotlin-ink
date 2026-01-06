package tech.derrickmwendwa.internal

import org.fusesource.jansi.Ansi
import org.fusesource.jansi.AnsiConsole
import tech.derrickmwendwa.style.AnsiBackground
import tech.derrickmwendwa.style.AnsiColor
import tech.derrickmwendwa.style.Style
import tech.derrickmwendwa.style.TextStyle

internal object AnsiRenderer {

    init {
        AnsiConsole.systemInstall()
    }

    fun render(
        textSegments: List<Pair<String, Style?>>,
        lineStyles: Map<Int, Style> = emptyMap()
    ): List<String> {
        val rows = (0 until CHARACTER_HEIGHT).map { StringBuilder() }

        // Construct the ASCII art rows segment by segment
        for ((text, style) in textSegments) {
            val charBlocks = text.map { characters[it]?.content ?: characters['?']!!.content }

            for (row in 0 until CHARACTER_HEIGHT) {
                val rowBuilder = StringBuilder()
                for (charBlock in charBlocks) {
                    // Prepend space as per original implementation
                    rowBuilder.append(" ").append(charBlock[row])
                }

                val lineStyle = lineStyles[row]
                val effectiveStyle = lineStyle?.merge(style) ?: style

                // If a style is present for this segment (merged), wrap the segment's row content in ANSI codes
                val styledPart = if (effectiveStyle != null) {
                    wrapWithAnsi(rowBuilder.toString(), effectiveStyle)
                } else {
                    rowBuilder.toString()
                }

                rows[row].append(styledPart)
            }
        }

        return rows.map { it.toString() }
    }

    private fun wrapWithAnsi(content: String, style: Style): String {
        val ansi = Ansi.ansi()

        // Apply Color
        style.color?.let {
            val ansiColor = mapColor(it)
            if (it.name.startsWith("BRIGHT")) {
                ansi.fgBright(ansiColor)
            } else {
                ansi.fg(ansiColor)
            }
        }

        // Apply Background
        style.background?.let {
            val ansiBg = mapBackground(it)
            if (it.name.startsWith("BRIGHT")) {
                ansi.bgBright(ansiBg)
            } else {
                ansi.bg(ansiBg)
            }
        }

        // Apply Text Styles
        style.styles.forEach { textStyle ->
            when (textStyle) {
                TextStyle.BOLD -> ansi.a(Ansi.Attribute.INTENSITY_BOLD)
                TextStyle.FAINT -> ansi.a(Ansi.Attribute.INTENSITY_FAINT)
                TextStyle.ITALIC -> ansi.a(Ansi.Attribute.ITALIC)
                TextStyle.UNDERLINE -> ansi.a(Ansi.Attribute.UNDERLINE)
                TextStyle.BLINK_SLOW -> ansi.a(Ansi.Attribute.BLINK_SLOW)
                TextStyle.BLINK_FAST -> ansi.a(Ansi.Attribute.BLINK_FAST)
                TextStyle.INVERSE -> ansi.a(Ansi.Attribute.NEGATIVE_ON)
                TextStyle.CONCEAL -> ansi.a(Ansi.Attribute.CONCEAL_ON)
                TextStyle.CROSSED_OUT -> ansi.a(Ansi.Attribute.STRIKETHROUGH_ON)
            }
        }

        return ansi.a(content).reset().toString()
    }

    private fun mapColor(color: AnsiColor): Ansi.Color {
        return when (color) {
            AnsiColor.BLACK, AnsiColor.BRIGHT_BLACK -> Ansi.Color.BLACK
            AnsiColor.RED, AnsiColor.BRIGHT_RED -> Ansi.Color.RED
            AnsiColor.GREEN, AnsiColor.BRIGHT_GREEN -> Ansi.Color.GREEN
            AnsiColor.YELLOW, AnsiColor.BRIGHT_YELLOW -> Ansi.Color.YELLOW
            AnsiColor.BLUE, AnsiColor.BRIGHT_BLUE -> Ansi.Color.BLUE
            AnsiColor.MAGENTA, AnsiColor.BRIGHT_MAGENTA -> Ansi.Color.MAGENTA
            AnsiColor.CYAN, AnsiColor.BRIGHT_CYAN -> Ansi.Color.CYAN
            AnsiColor.WHITE, AnsiColor.BRIGHT_WHITE -> Ansi.Color.WHITE
            AnsiColor.DEFAULT -> Ansi.Color.DEFAULT
        }
    }

    private fun mapBackground(color: AnsiBackground): Ansi.Color {
        return when (color) {
            AnsiBackground.BLACK, AnsiBackground.BRIGHT_BLACK -> Ansi.Color.BLACK
            AnsiBackground.RED, AnsiBackground.BRIGHT_RED -> Ansi.Color.RED
            AnsiBackground.GREEN, AnsiBackground.BRIGHT_GREEN -> Ansi.Color.GREEN
            AnsiBackground.YELLOW, AnsiBackground.BRIGHT_YELLOW -> Ansi.Color.YELLOW
            AnsiBackground.BLUE, AnsiBackground.BRIGHT_BLUE -> Ansi.Color.BLUE
            AnsiBackground.MAGENTA, AnsiBackground.BRIGHT_MAGENTA -> Ansi.Color.MAGENTA
            AnsiBackground.CYAN, AnsiBackground.BRIGHT_CYAN -> Ansi.Color.CYAN
            AnsiBackground.WHITE, AnsiBackground.BRIGHT_WHITE -> Ansi.Color.WHITE
            AnsiBackground.DEFAULT -> Ansi.Color.DEFAULT
        }
    }
}
