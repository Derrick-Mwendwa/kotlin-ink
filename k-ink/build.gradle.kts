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
            create<MavenPublication>("release") {
                from(components["release"])

                groupId = project.group.toString()
                artifactId = project.name
                version = project.version.toString()
            }
        }
    }
}