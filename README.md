# KInk

KInk is a simple ASCII art library written in Kotlin. It allows you to print text in ASCII art format.

## Features

- Supports a wide range of ASCII characters.
- Easy to use with a simple API.
- Written in Kotlin, making it compatible with Java and other JVM languages.

## Usage

To use KInk, you simply need to call the `say` function with the text you want to print in ASCII art. Here's an example:

```kotlin
KInk.say("Hello, World!")
```

This will print the text "Hello, World!" in ASCII art.

Please note that not all characters are supported. If you try to print a character that is not supported,
an `IllegalArgumentException` will be thrown.

## Installation

### JitPack

[![](https://jitpack.io/v/Derrick-Mwendwa/KotlinInk.svg)](https://jitpack.io/#Derrick-Mwendwa/KotlinInk)

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
    implementation("tech.derrickmwendwa:kotlin-ink:1.0.3")
}
```

## Contributing

Contributions are welcome! Please feel free to submit a pull request if you have any improvements or open an issue if
you encounter any problems.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
