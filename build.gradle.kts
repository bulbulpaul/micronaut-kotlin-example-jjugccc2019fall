import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.50"
    kotlin("kapt") version "1.3.50"
    kotlin("plugin.allopen") version "1.3.50"
    id("com.github.johnrengelman.shadow") version "5.0.0"
    id("org.jlleitschuh.gradle.ktlint") version "8.0.0"
    id("application")
}

version = "0.1"
group = "com.merrylab.example"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
    mavenCentral()
    maven("https://jcenter.bintray.com")
}

val developmentOnly by configurations.creating
configurations {
    runtimeClasspath {
        extendsFrom(developmentOnly)
    }
    testRuntimeClasspath {
        extendsFrom(developmentOnly)
    }
}

dependencies {
    val kotlinVersion: String by project
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
    implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")

    val micronautVersion: String by project
    implementation(platform("io.micronaut:micronaut-bom:$micronautVersion"))
    implementation("io.micronaut:micronaut-http-client")

    val micronautInjectJavaVersion: String by project
    annotationProcessor("io.micronaut:micronaut-inject-java:$micronautInjectJavaVersion")
    kapt("io.micronaut:micronaut-inject-java:$micronautInjectJavaVersion")
    kaptTest("io.micronaut:micronaut-inject-java:$micronautInjectJavaVersion")
    implementation("io.micronaut:micronaut-inject")

    kapt("io.micronaut:micronaut-validation")

    kapt(platform("io.micronaut:micronaut-bom:$micronautVersion"))
    kaptTest(platform("io.micronaut:micronaut-bom:$micronautVersion"))
    testImplementation(platform("io.micronaut:micronaut-bom:$micronautVersion"))

    // apply Kotlin Runtime Support
    implementation("io.micronaut.kotlin:micronaut-kotlin-runtime:1.0.0.M2")
    implementation("io.micronaut.kotlin:micronaut-ktor:1.0.0.M2")

    val ktorVersion: String by project
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("io.ktor:ktor-jackson:$ktorVersion")
    runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin:2.9.8")

    val spekVersion: String by project
    testImplementation("org.jetbrains.kotlin:kotlin-test:$kotlinVersion")
    testImplementation("org.spekframework.spek2:spek-dsl-jvm:$spekVersion") {
        exclude("org.jetbrains.kotlin")
    }
    testRuntimeOnly("org.spekframework.spek2:spek-runner-junit5:$spekVersion") {
        exclude("org.junit.platform")
        exclude("org.jetbrains.kotlin")
    }
    testImplementation("org.junit.jupiter:junit-jupiter-engine")
    testRuntimeOnly("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")

    testImplementation("io.mockk:mockk:1.9.3")

    runtimeOnly("ch.qos.logback:logback-classic:1.2.3")
}

application {
    mainClassName = "com.merrylab.example.ApplicationKt"
    applicationDefaultJvmArgs = listOf("-noverify", "-XX:TieredStopAtLevel=1", "-Dcom.sun.management.jmxremote")
}

tasks {
    test {
        useJUnitPlatform {
            includeEngines = setOf("spek2")
        }
    }
}

allOpen {
    annotation("io.micronaut.aop.Around")
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "1.8"
            javaParameters = true
        }
    }
}

tasks.withType<ShadowJar> {
    mergeServiceFiles()
}
