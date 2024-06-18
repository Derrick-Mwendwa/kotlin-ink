package tech.derrickmwendwa

import tech.derrickmwendwa.internal.CHARACTER_HEIGHT
import tech.derrickmwendwa.internal.characters
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
    }
}