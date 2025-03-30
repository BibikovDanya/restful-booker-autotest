plugins {
    id("java")
    kotlin("jvm")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation(dependencyNotation = "io.rest-assured:rest-assured:5.5.1")
    testImplementation("com.fasterxml.jackson.core", "jackson-databind", "2.18.1")
    testImplementation(dependencyNotation = "org.testcontainers:junit-jupiter:1.20.6")
    implementation(kotlin("stdlib-jdk8"))
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.18.3")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.18.3")
}


tasks.test {
    useJUnitPlatform()
}
tasks.test {}
tasks.register<Test>("modelTests") {
    useJUnitPlatform {
        includeTags("model")
    }
}



tasks.register<Test>("allBookTest") {
    useJUnitPlatform {
        includeTags("GetBooking", "GetBookingIds", "CreateBooking", "UpdateBooking", "PartialUpdateBooking")
    }
    testLogging {
        events("passed", "skipped", "failed", "standardOut", "standardError")
    }
}
kotlin {
    jvmToolchain(21)
}