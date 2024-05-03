group = "tech.derrickmwendwa"
version = "1.0.0"

plugins {
    kotlin("jvm") version "1.9.23"
    id("maven-publish")
}

repositories {
    mavenCentral()
}

tasks.jar {
    manifest {
        attributes(
            mapOf(
                "Implementation-Title" to project.name,
                "Implementation-Version" to project.version
            )
        )
    }
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("mavenJava") {
                from(components["java"])

                groupId = project.group.toString()
                artifactId = project.name
                version = project.version.toString()

                pom {
                    name.set("Kotlin Ink")
                    description.set("A simple ASCII art library written in Kotlin")
                    url.set("https://github.com/Derrick-Mwendwa/KotlinInk")
                    licenses {
                        license {
                            name.set("MIT License")
                            url.set("https://github.com/Derrick-Mwendwa/KotlinInk/blob/main/LICENSE")
                        }
                    }
                    developers {
                        developer {
                            id.set("Derrick-Mwendwa")
                            name.set("Derrick Mwendwa")
                            email.set("derekmwendwa14@gmail.com")
                        }
                    }
                    scm {
                        connection.set("scm:git:git://github.com/Derrick-Mwendwa/KotlinInk.git")
                        developerConnection.set("scm:git:ssh://github.com/Derrick-Mwendwa/KotlinInk.git")
                        url.set("https://github.com/Derrick-Mwendwa/KotlinInk/tree/main")
                    }
                }
            }
        }
    }
}