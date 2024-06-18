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
    implementation("com.github.Derrick-Mwendwa:kotlin-ink:1.1.0")
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

## Contributing

Contributions are welcome! Please feel free to submit a pull request if you have any improvements or open an issue if
you encounter any problems.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
