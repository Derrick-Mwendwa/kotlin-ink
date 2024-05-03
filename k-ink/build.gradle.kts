group = "tech.derrickmwendwa"
version = "1.0.0"

plugins {
    kotlin("jvm") version "1.9.23"
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