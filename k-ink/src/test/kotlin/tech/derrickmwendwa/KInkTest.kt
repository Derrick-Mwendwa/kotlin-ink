package tech.derrickmwendwa

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class KInkTest {

    @Test
    fun `say prints correct ASCII art for valid text`() {
        val outputStream = ByteArrayOutputStream()
        KInk.say("A", outputStream)
        val output = outputStream.toString().trim()

        // 'A' has 6 lines of height
        val lines = output.split(System.lineSeparator())
        // Depending on platform line separator, split might vary, but trim() helps.
        // However, KInk.say printlns each line.

        // Let's look at the expected output for 'A' from Characters.kt
        //    _
        //   / \
        //  / _ \
        // / ___ \
        // /_/   \_\
        //

        assertTrue(lines.isNotEmpty())
        assertTrue(output.contains("/ ___ \\"))
    }

    @Test
    fun `say throws exception for invalid characters`() {
        assertThrows(IllegalArgumentException::class.java) {
            KInk.say("Hello\u0000") // Null char shouldn't exist in the map
        }
    }

    @Test
    fun `say supports custom output function`() {
        val lines = mutableListOf<String>()
        KInk.say("B") { line ->
            lines.add(line)
        }

        assertEquals(6, lines.size)
        // Check a distinctive part of B
        assertTrue(lines.any { it.contains("| |_) |") })
    }
}
