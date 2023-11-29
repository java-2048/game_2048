plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "8.1.1"
    application
}

group = "io.github.java_2048"
version = "1.0-SNAPSHOT"

java {
    toolchain{
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

application {
    mainClass.set("io.github.java_2048.game_2048.App")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.code.gson:gson:2.10.1")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

tasks.shadowJar {
    minimize()
}