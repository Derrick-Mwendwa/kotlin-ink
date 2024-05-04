package tech.derrickmwendwa.utils

/**
 * Marks a class, function, or property as part of the public API
 */
@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY
)
internal annotation class PublicApi

/**
 * Get a substring of a string using UTF-8 encoding
 * @receiver The string to convert
 * @param startIndex The start index of the substring
 * @param endIndex The end index of the substring
 * @return Substring of the original string
 */
internal fun String.safeSubstring(startIndex: Int, endIndex: Int): String {
    if (startIndex > endIndex)
        throw IllegalArgumentException("Start index must be less or equal to end index.")
    if (startIndex < 0 || endIndex > this.length)
        throw IndexOutOfBoundsException("Start or end index are out of bounds.")

    val codePointCount = this.codePointCount(0, this.length)
    if (startIndex >= codePointCount || endIndex > codePointCount)
        throw IllegalArgumentException("Start or end index are out of code point bounds.")

    val realStart = this.offsetByCodePoints(0, startIndex)
    val realEnd = this.offsetByCodePoints(0, endIndex)

    return this.substring(realStart, realEnd)
}