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
        includeTagsCsv: String = "",
        excludeTagsCsv: String = ""
    ) = register<Test>(name) {
        useJUnitPlatform {
            val includeTags = includeTagsCsv.split(",")
                .map { it.trim() }
                .filter { it.isNotEmpty() }
                .toTypedArray()
            val excludeTags = excludeTagsCsv.split(",")
                .map { it.trim() }
                .filter { it.isNotEmpty() }
                .toTypedArray()

            if (includeTags.isNotEmpty()) {
                includeTags(*includeTags)
            }
            if (excludeTags.isNotEmpty()) {
                excludeTags(*excludeTags)
            }
        }
        group = "Verification"
        description = "Runs tests including tags [$includeTagsCsv] and excluding tags [$excludeTagsCsv]"
    }

    val modelTests by createTaggedTestTask("modelTests", includeTagsCsv = "model")
    val allTest by createTaggedTestTask("allTest", includeTagsCsv = "auth, book, security")
    val allStableTest by createTaggedTestTask("allStableTest", includeTagsCsv = "auth, book, security", excludeTagsCsv = "flaky, slow")
    val bookTest by createTaggedTestTask("bookTests", includeTagsCsv = "book")
    val authTest by createTaggedTestTask("authTests", includeTagsCsv = "auth")
    val securityTest by createTaggedTestTask("securityTest", includeTagsCsv = "security")
}




kotlin {
    jvmToolchain(21)
}