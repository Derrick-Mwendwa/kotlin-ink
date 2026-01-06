package tech.derrickmwendwa.internal

import tech.derrickmwendwa.internal.ASPECT_RATIO
import tech.derrickmwendwa.internal.Pattern
import tech.derrickmwendwa.internal.SolidPattern
import tech.derrickmwendwa.internal.buildShapeString
import kotlin.math.pow
import kotlin.math.sqrt

internal object ShapeRasterizer {

    fun rectangle(width: Int, height: Int, pattern: Pattern): String {
        val lines = mutableListOf<String>()
        for (y in 0 until height) {
            val line = StringBuilder()
            for (x in 0 until width) {
                line.append(pattern.getChar(x, y))
            }
            lines.add(line.toString())
        }
        return buildShapeString(lines)
    }

    fun triangle(height: Int, pattern: Pattern): String {
        // Isosceles triangle
        // Height is h.
        // Base width needs to be calculated.
        // If we want a symmetric triangle, we need odd width usually.
        // Let's assume slopes of 1 (ignoring aspect ratio for the slope logic itself first, or not?)
        // Standard ASCII triangle:
        //   *
        //  ***
        // *****
        // This has height 3, width 5.
        // Width = 2 * (Height - 1) + 1

        val lines = mutableListOf<String>()
        val width = 2 * (height - 1) + 1
        val mid = width / 2

        for (y in 0 until height) {
            val line = StringBuilder()
            // Triangle width at this row
            // Row 0: 1 char (at mid)
            // Row 1: 3 chars (mid-1 to mid+1)
            // Row y: 2*y + 1 chars (mid-y to mid+y)

            for (x in 0 until width) {
                if (x >= mid - y && x <= mid + y) {
                    line.append(pattern.getChar(x, y))
                } else {
                    line.append(' ')
                }
            }
            lines.add(line.toString())
        }
        return buildShapeString(lines)
    }

    fun circle(radius: Int, pattern: Pattern): String {
        // x^2 + y^2 <= r^2
        // Correct for aspect ratio.
        // Distance calculation: (x - cx)^2 + (aspect * (y - cy))^2 <= r^2

        // Width will be 2*radius usually, but with aspect ratio, width might need to be adjusted
        // if radius is defined in vertical units?
        // Usually radius is defined in terms of the "limiting" dimension or just abstract units.
        // If we want a circle that looks round, and we specify radius R:
        // Height should be roughly 2*R
        // Width should be roughly 2*R * ASPECT_RATIO

        // Let's define radius as the vertical radius (rows).
        val height = 2 * radius + 1
        val width = (height * ASPECT_RATIO).toInt()

        val cy = radius
        val cx = width / 2

        val lines = mutableListOf<String>()

        for (y in 0 until height) {
            val line = StringBuilder()
            for (x in 0 until width) {
                // Normalize coordinates to center (0,0)
                val dy = y - cy
                // We need to map x (0..width) to aspect-corrected space
                // Or rather, we just check if the point is within the ellipse that visually represents a circle.
                // dx needs to be scaled down by aspect ratio to match dy units
                val dx = (x - cx) / ASPECT_RATIO

                if (dx * dx + dy * dy <= radius.toDouble().pow(2.0)) {
                    line.append(pattern.getChar(x, y))
                } else {
                    line.append(' ')
                }
            }
            lines.add(line.toString())
        }
        return buildShapeString(lines)
    }

    fun polygon(sides: Int, radius: Int, pattern: Pattern): String {
        // General polygon logic
        // Center at (cx, cy)
        // Vertices at (r * cos(theta), r * sin(theta))
        // Rotate so one vertex is up?

        if (sides < 3) return ""

        val height = 2 * radius + 1
        val width = (height * ASPECT_RATIO).toInt()
        val cy = radius.toDouble()
        val cx = width / 2.0

        // Calculate vertices
        val vertices = mutableListOf<Pair<Double, Double>>()
        val angleStep = 2 * Math.PI / sides
        // Start angle: -PI/2 (top)
        val startAngle = -Math.PI / 2

        for (i in 0 until sides) {
            val angle = startAngle + i * angleStep
            // x coord needs to be scaled by aspect ratio for display
            val vx = cx + (radius * Math.cos(angle)) * ASPECT_RATIO
            val vy = cy + (radius * Math.sin(angle))
            vertices.add(vx to vy)
        }

        val lines = mutableListOf<String>()

        for (y in 0 until height) {
            val line = StringBuilder()
            for (x in 0 until width) {
                if (isPointInPolygon(x.toDouble(), y.toDouble(), vertices)) {
                    line.append(pattern.getChar(x, y))
                } else {
                    line.append(' ')
                }
            }
            lines.add(line.toString())
        }
        return buildShapeString(lines)
    }

    private fun isPointInPolygon(x: Double, y: Double, vertices: List<Pair<Double, Double>>): Boolean {
        var inside = false
        var j = vertices.size - 1
        for (i in vertices.indices) {
            val xi = vertices[i].first
            val yi = vertices[i].second
            val xj = vertices[j].first
            val yj = vertices[j].second

            val intersect = ((yi > y) != (yj > y)) &&
                    (x < (xj - xi) * (y - yi) / (yj - yi) + xi)
            if (intersect) inside = !inside
            j = i
        }
        return inside
    }
}
