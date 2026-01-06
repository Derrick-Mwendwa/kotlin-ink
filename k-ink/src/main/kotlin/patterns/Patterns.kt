package tech.derrickmwendwa.patterns

/**
 * Interface for defining a pattern to fill a shape.
 */
interface Pattern {
    /**
     * Returns the character at the specified coordinates.
     * @param x The x-coordinate (column)
     * @param y The y-coordinate (row)
     * @return The character to draw
     */
    fun getChar(x: Int, y: Int): Char
}

/**
 * A solid pattern that fills the shape with a single character.
 * @param char The character to fill the shape with
 */
class SolidPattern(private val char: Char) : Pattern {
    override fun getChar(x: Int, y: Int): Char = char
}

/**
 * A checkered pattern that alternates between two characters.
 * @param char1 The first character
 * @param char2 The second character
 */
class CheckeredPattern(private val char1: Char, private val char2: Char) : Pattern {
    override fun getChar(x: Int, y: Int): Char = if ((x + y) % 2 == 0) char1 else char2
}
