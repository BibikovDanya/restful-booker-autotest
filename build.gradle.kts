plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("io.rest-assured:rest-assured:5.5.1")
    testImplementation("com.fasterxml.jackson.core", "jackson-databind", "2.18.1")
    testImplementation("org.testcontainers:junit-jupiter:1.20.6")


}

tasks.test {
    useJUnitPlatform()
}
tasks.register<Test>("modelTests") {
    useJUnitPlatform {
        includeTags("model")
    }
}

tasks.register<Test>("apiTests") {
    useJUnitPlatform {
        includeTags("api")
    }
    testLogging {
        events("passed", "skipped", "failed", "standardOut", "standardError")
    }
}