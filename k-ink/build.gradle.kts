group = "tech.derrickmwendwa"
version = "1.1.1"

plugins {
    kotlin("jvm") version "1.9.23"
    id("maven-publish")
}

repositories {
    mavenCentral()
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = project.name
            version = project.version.toString()
            from(components["kotlin"])
        }
    }
}