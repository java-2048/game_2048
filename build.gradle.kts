plugins {
    id("java")
}

group = "io.github.java_2048"
version = "1.0-SNAPSHOT"

java {
    version
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

tasks.jar{
    manifest.attributes["Main-Class"] = "io.github.java_2048.game_2048.App"
}