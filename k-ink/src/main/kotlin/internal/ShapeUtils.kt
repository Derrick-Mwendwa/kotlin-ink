package tech.derrickmwendwa.internal

internal const val ASPECT_RATIO = 2.0

// Helper to construct the string from a grid/list of strings
internal fun buildShapeString(lines: List<String>): String {
    return lines.joinToString(System.lineSeparator())
}
