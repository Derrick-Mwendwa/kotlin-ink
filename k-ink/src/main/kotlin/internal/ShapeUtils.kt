package tech.derrickmwendwa.internal

internal const val ASPECT_RATIO = 2.0

sealed interface Pattern {
    fun getChar(x: Int, y: Int): Char
}

class SolidPattern(private val char: Char) : Pattern {
    override fun getChar(x: Int, y: Int): Char = char
}

class CheckeredPattern(private val char1: Char, private val char2: Char) : Pattern {
    override fun getChar(x: Int, y: Int): Char = if ((x + y) % 2 == 0) char1 else char2
}

// Helper to construct the string from a grid/list of strings
internal fun buildShapeString(lines: List<String>): String {
    return lines.joinToString(System.lineSeparator())
}
