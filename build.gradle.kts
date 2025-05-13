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

    //Log4J
    implementation("org.apache.logging.log4j:log4j-api:2.24.3")
    implementation("org.apache.logging.log4j:log4j-core:2.24.3")
    implementation("org.apache.logging.log4j:log4j-slf4j2-impl:2.24.3")
}


tasks {
    test {
        useJUnitPlatform()
        testLogging {
            events("passed", "skipped", "failed", "standardOut", "standardError")
        }
        reports {
            junitXml.required = true
            html.required = true
        }
        enableAssertions = true
    }

    fun createTaggedTestTask(
        name: String,
        vararg tags: String,
    ) = register<Test>(name) {
        useJUnitPlatform {
            includeTags(*tags)
            excludeTags("flaky")

        }
        group = "Verification"
        description = "Runs tests tagged with '${tags.joinToString()}'"
    }

    val modelTests by createTaggedTestTask("modelTests", "model")
    val allTest by createTaggedTestTask("allTest", "auth", "book", "security")
    val allStableTest by createTaggedTestTask("allStableTest", "auth", "book", "security")
    val bookTest by createTaggedTestTask("bookTests", "book")
    val authTest by createTaggedTestTask("authTests", "auth")
}



kotlin {
    jvmToolchain(21)
}