package tech.derrickmwendwa

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import tech.derrickmwendwa.patterns.CheckeredPattern

class KInkShapesTest {

    @Test
    fun `rectangle generates correct dimensions`() {
        val rect = KInk.rectangle(10, 5, '*')
        val lines = rect.split(System.lineSeparator())
        assertEquals(5, lines.size)
        assertTrue(lines.all { it.length == 10 })
        assertTrue(lines.all { it == "**********" })
    }

    @Test
    fun `square generates correct dimensions`() {
        val sq = KInk.square(5, '#')
        val lines = sq.split(System.lineSeparator())
        assertEquals(5, lines.size)
        assertTrue(lines.all { it.length == 5 })
        assertTrue(lines.all { it == "#####" })
    }

    @Test
    fun `rectangle with checkered pattern`() {
        val pattern = CheckeredPattern('X', 'O')
        val rect = KInk.rectangle(4, 2, pattern)
        val lines = rect.split(System.lineSeparator())

        // Row 0: 0+0=0(X), 1+0=1(O), 2+0=0(X), 3+0=1(O) -> XOXO
        // Row 1: 0+1=1(O), 1+1=2(X), 2+1=3(O), 3+1=4(X) -> OXOX

        assertEquals("XOXO", lines[0])
        assertEquals("OXOX", lines[1])
    }

    @Test
    fun `triangle generates correct shape`() {
        // Height 3 -> Width 5 (2*(3-1)+1)
        val tri = KInk.triangle(3, '*')
        val lines = tri.split(System.lineSeparator())

        assertEquals(3, lines.size)
        // Expected:
        //   *
        //  ***
        // *****
        // Note: my implementation pads with spaces to make it a rectangular block of strings?
        // Let's check logic:
        // for x in 0 until width
        // if condition append char else append ' '

        assertEquals("  *  ", lines[0])
        assertEquals(" *** ", lines[1])
        assertEquals("*****", lines[2])
    }

    @Test
    fun `circle generates somewhat round shape`() {
        // Radius 3 -> Height 7, Width 14
        val circle = KInk.circle(3, '*')
        val lines = circle.split(System.lineSeparator())

        assertEquals(7, lines.size)
        // With aspect ratio 2.0, width should be around 14
        assertEquals(14, lines[0].length)

        // Center should be filled
        val midRow = lines[3]
        assertTrue(midRow.trim().length > 0)
        assertTrue(midRow.contains("*****")) // Should definitely be filled in the middle

        // Corners should be empty
        assertTrue(lines[0].startsWith("  "))
        assertTrue(lines[0].endsWith("  "))
    }

    @Test
    fun `polygon generates non-empty string for pentagon`() {
        val poly = KInk.polygon(5, 5, '*')
        assertTrue(poly.isNotEmpty())
        val lines = poly.split(System.lineSeparator())
        // Should have height approx 2*radius+1 = 11
        assertEquals(11, lines.size)
        // Center row should have content
        val midRow = lines[5]
        assertTrue(midRow.trim().isNotEmpty())
    }
}
