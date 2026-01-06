package tech.derrickmwendwa

import org.fusesource.jansi.Ansi
import org.fusesource.jansi.AnsiConsole
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import tech.derrickmwendwa.style.AnsiColor
import tech.derrickmwendwa.style.Style
import tech.derrickmwendwa.style.TextStyle
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class ColorTest {
    private val outContent = ByteArrayOutputStream()
    private val originalOut = System.out

    @BeforeEach
    fun setUp() {
        // Uninstall JAnsi to prevent it from wrapping System.out and interfering with capture
        AnsiConsole.systemUninstall()
        // Force ANSI codes to be generated even if not a TTY
        Ansi.setEnabled(true)
        System.setOut(PrintStream(outContent, true))
    }

    @AfterEach
    fun tearDown() {
        System.setOut(originalOut)
    }

    @Test
    fun `test say with single color`() {
        KInk.say("A", Style(color = AnsiColor.RED))

        val output = outContent.toString()

        assertTrue(output.contains("\u001B[31m"), "Output should contain Red ANSI code")
        // JAnsi might emit [m instead of [0m
        assertTrue(output.contains("\u001B[0m") || output.contains("\u001B[m"), "Output should contain Reset ANSI code")
    }

    @Test
    fun `test say with DSL and mixed colors`() {
        KInk.say {
            text("A", Style(color = AnsiColor.RED))
            text("B", Style(color = AnsiColor.BLUE))
        }

        val output = outContent.toString()
        assertTrue(output.contains("\u001B[31m"), "Output should contain Red ANSI code")
        assertTrue(output.contains("\u001B[34m"), "Output should contain Blue ANSI code")
    }

    @Test
    fun `test say with withStyle block`() {
        KInk.say {
            withStyle(color = AnsiColor.GREEN) {
                text("C")
            }
        }

        val output = outContent.toString()
        assertTrue(output.contains("\u001B[32m"), "Output should contain Green ANSI code")
    }

    @Test
    fun `test line style`() {
        KInk.say {
            text("D")
            styleLine(0, Style(color = AnsiColor.YELLOW))
        }

        val output = outContent.toString()
        assertTrue(output.contains("\u001B[33m"), "Output should contain Yellow ANSI code")
    }
}
