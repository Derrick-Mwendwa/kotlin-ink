> [!IMPORTANT]
> The dependency has changed from `tech.derrickmwendwa:kotlin-ink:version` to `com.github.Derrick-Mwendwa:kotlin-ink:version`. Please update your `build.gradle.kts` file accordingly.

# KInk (Kotlin Ink)

KInk (Kotlin Ink) is a lightweight Kotlin library for generating ASCII art. It provides a simple API to convert text
into ASCII art.

## Installation

### Jitpack

[![](https://jitpack.io/v/Derrick-Mwendwa/kotlin-ink.svg)](https://jitpack.io/#Derrick-Mwendwa/kotlin-ink)

KInk is available on JitPack. You can add it to your project by following the instructions below.

To add KInk to your project, you need to add the JitPack repository to your `build.gradle.kts` file:

```kotlin
repositories {
    maven("https://jitpack.io")
}
```

Next, add KInk as a dependency:

```kotlin
dependencies {
    implementation("com.github.Derrick-Mwendwa:kotlin-ink:1.1.1")
}
```

## Usage

KInk provides several methods to print ASCII art from text. Below are examples demonstrating how to use each method.

### Print ASCII Art to Console

To print text as ASCII art directly to the console, use the `say` method:

```kotlin
import tech.derrickmwendwa.KInk

fun main() {
    KInk.say("Hello, World!")
}
```

### Print ASCII Art to an OutputStream

You can also direct the ASCII art to any `OutputStream`:

```kotlin
import tech.derrickmwendwa.KInk

fun main() {
    KInk.say("Hello, World!", System.out)
}
```

### Custom Output Function

For more control over the output, you can provide a custom output function:

```kotlin
import tech.derrickmwendwa.KInk

fun main() {
    KInk.say("Hello, World!") { line ->
        println(line) 
    }
}
```

The lambda function receives line by line ASCII art output, not the entire text.

> [!CAUTION]
> Please note that not all characters are supported. If you try to print a character that is not supported, an `IllegalArgumentException` will be thrown.

### Basic Shapes and Patterns

KInk now supports generating basic shapes like rectangles, squares, triangles, circles, and polygons. You can also specify patterns for filling these shapes.

```kotlin
import tech.derrickmwendwa.KInk
import tech.derrickmwendwa.patterns.CheckeredPattern

fun main() {
    // Print a rectangle
    KInk.printRectangle(10, 5)

    // Print a square with a custom character
    KInk.printSquare(8, '#')

    // Print a circle
    KInk.printCircle(5)

    // Print a triangle with a checkered pattern
    val checkered = CheckeredPattern('X', 'O')
    KInk.printTriangle(10, checkered)

    // Print a polygon (Pentagon)
    KInk.printPolygon(5, 7)
}
```

## Upcoming Features

- Image to ASCII Art
- Color Support
- Performance Optimizations
- Documentation

## Contributing

Contributions are welcome! Please feel free to submit a pull request if you have any improvements or open an issue if
you encounter any problems.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
