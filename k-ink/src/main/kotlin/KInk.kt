package tech.derrickmwendwa

import tech.derrickmwendwa.internal.CHARACTER_HEIGHT
import tech.derrickmwendwa.internal.characters

/**
 * KInk is a simple ASCII art library
 */
class KInk private constructor() {
    companion object {
        /**
         * Prints text in ASCII art
         * @param text The text to print
         */
        fun say(text: String) {
            val charArray = text.toCharArray()

            // Check if the character(s) exists
            charArray.forEach { ch ->
                if (characters[ch] == null) {
                    throw IllegalArgumentException(
                        "Character '$ch' in \"$text\" does not exist in KInk"
                    )
                }
            }

            // Print the character(s)
            for (row in 0 until CHARACTER_HEIGHT) {
                charArray.forEach { ch ->
                    print(" ${characters[ch]!!.content[row]}")
                }
                println(" ")
            }
        }
    }
}