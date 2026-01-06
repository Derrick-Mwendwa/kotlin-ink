package tech.derrickmwendwa

import tech.derrickmwendwa.internal.CHARACTER_HEIGHT
import tech.derrickmwendwa.internal.ShapeRasterizer
import tech.derrickmwendwa.internal.characters
import tech.derrickmwendwa.patterns.CheckeredPattern
import tech.derrickmwendwa.patterns.Pattern
import tech.derrickmwendwa.patterns.SolidPattern
import tech.derrickmwendwa.utils.PublicApi
import tech.derrickmwendwa.utils.safeSubstring
import java.io.OutputStream
import java.io.PrintStream

/**
 * KInk is a simple ASCII art library
 */
@PublicApi
class KInk private constructor() {
    companion object {
        /**
         * Prints text in ASCII art using a custom output function
         * @param text The text to print
         * @param output The function to output each line of the ASCII art
         */
        @PublicApi
        fun say(text: String, output: (String) -> Unit) {
            // Check if the character(s) exists
            validateCharacters(text)

            // Output each line of the ASCII art
            for (row in 0 until CHARACTER_HEIGHT) {
                val line = StringBuilder()
                text.forEach { ch ->
                    line.append(" ${characters[ch]!!.content[row]}")
                }
                output(line.toString())
            }
        }

        /**
         * Prints text in ASCII art to an output stream
         * @param text The text to print
         * @param outputStream The output stream to print the ASCII art
         */
        @PublicApi
        fun say(text: String, outputStream: OutputStream) {
            say(text) { PrintStream(outputStream).println(it) }
        }

        /**
         * Prints text in ASCII art
         * @param text The text to print
         */
        @PublicApi
        fun say(text: String) {
            say(text) { println(it) }
        }

        /**
         * Validates if the characters in the text exist in Kotlin Ink
         * @param text The text to validate
         */
        private fun validateCharacters(text: String) {
            text.forEachIndexed { index, ch ->
                if (characters[ch] == null) {
                    throw IllegalArgumentException(
                        "Character '${
                            text.safeSubstring(index, index + 1)
                        }' in \"$text\" does not exist in Kotlin Ink"
                    )
                }
            }
        }

        // Shapes

        /**
         * Generates a rectangle string.
         * @param width The width of the rectangle
         * @param height The height of the rectangle
         * @param pattern The pattern to fill the rectangle with (default is Solid '*')
         * @return The rectangle as a String
         */
        @PublicApi
        fun rectangle(width: Int, height: Int, pattern: Pattern = SolidPattern('*')): String {
            return ShapeRasterizer.rectangle(width, height, pattern)
        }

        @PublicApi
        fun rectangle(width: Int, height: Int, char: Char): String {
            return rectangle(width, height, SolidPattern(char))
        }

        /**
         * Prints a rectangle to the console.
         */
        @PublicApi
        fun printRectangle(width: Int, height: Int, pattern: Pattern = SolidPattern('*')) {
            println(rectangle(width, height, pattern))
        }

        /**
         * Generates a square string.
         * @param size The size of the square (width and height)
         * @param pattern The pattern to fill the square with
         * @return The square as a String
         */
        @PublicApi
        fun square(size: Int, pattern: Pattern = SolidPattern('*')): String {
            return rectangle(size, size, pattern)
        }

        @PublicApi
        fun square(size: Int, char: Char): String {
            return square(size, SolidPattern(char))
        }

        @PublicApi
        fun printSquare(size: Int, pattern: Pattern = SolidPattern('*')) {
            println(square(size, pattern))
        }

        /**
         * Generates a triangle string.
         * @param height The height of the triangle
         * @param pattern The pattern to fill the triangle with
         * @return The triangle as a String
         */
        @PublicApi
        fun triangle(height: Int, pattern: Pattern = SolidPattern('*')): String {
            return ShapeRasterizer.triangle(height, pattern)
        }

        @PublicApi
        fun triangle(height: Int, char: Char): String {
            return triangle(height, SolidPattern(char))
        }

        @PublicApi
        fun printTriangle(height: Int, pattern: Pattern = SolidPattern('*')) {
            println(triangle(height, pattern))
        }

        /**
         * Generates a circle string.
         * @param radius The radius of the circle
         * @param pattern The pattern to fill the circle with
         * @return The circle as a String
         */
        @PublicApi
        fun circle(radius: Int, pattern: Pattern = SolidPattern('*')): String {
            return ShapeRasterizer.circle(radius, pattern)
        }

        @PublicApi
        fun circle(radius: Int, char: Char): String {
            return circle(radius, SolidPattern(char))
        }

        @PublicApi
        fun printCircle(radius: Int, pattern: Pattern = SolidPattern('*')) {
            println(circle(radius, pattern))
        }

        /**
         * Generates a polygon string.
         * @param sides The number of sides
         * @param radius The radius (approximate size)
         * @param pattern The pattern to fill the polygon with
         * @return The polygon as a String
         */
        @PublicApi
        fun polygon(sides: Int, radius: Int, pattern: Pattern = SolidPattern('*')): String {
            return ShapeRasterizer.polygon(sides, radius, pattern)
        }

        @PublicApi
        fun polygon(sides: Int, radius: Int, char: Char): String {
            return polygon(sides, radius, SolidPattern(char))
        }

        @PublicApi
        fun printPolygon(sides: Int, radius: Int, pattern: Pattern = SolidPattern('*')) {
            println(polygon(sides, radius, pattern))
        }
    }
}