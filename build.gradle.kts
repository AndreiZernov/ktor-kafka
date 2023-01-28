import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val ktorVersion: String by project
val kotlinVersion: String by project
val logbackVersion: String by project
val avroVersion: String by project

plugins {
    application
    kotlin("jvm") version "1.7.10"
    id("io.ktor.plugin") version "2.2.1"
    kotlin("plugin.serialization") version "1.7.10"
}

group = "ktor.kafka"
version = "1.0.0"

repositories {
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
    maven { url = uri("https://packages.confluent.io/maven/") }
}

dependencies {
    // server
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("io.ktor:ktor-server-core:$ktorVersion")
    implementation("io.ktor:ktor-server-host-common:$ktorVersion")
    implementation("io.ktor:ktor-server-content-negotiation:$ktorVersion")

    // kafka
    implementation("org.apache.kafka:kafka-clients:2.6.3")

    // logging
    implementation("io.github.microutils:kotlin-logging:1.7.7")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")

    // serialization
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")

    // avro
    implementation("org.apache.avro:avro:$avroVersion")
    implementation("io.confluent:kafka-avro-serializer:7.3.1")
    implementation("com.github.avro-kotlin.avro4k:avro4k-core:1.6.0")

    // test
    testImplementation("io.ktor:ktor-server-tests:$ktorVersion")
}

application {
    mainClass.set("ktor.kafka.ApplicationKt")
}

tasks.withType<KotlinCompile>() {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}
