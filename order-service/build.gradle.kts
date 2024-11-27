plugins {
    java
    id("org.springframework.boot") version "3.2.7"
    id("io.spring.dependency-management") version "1.1.6"
}

group = "ru.aston"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

val lombokVersion = "1.18.20"
val mapstructVersion = "1.5.5.Final"
val lombokMapstructBindingVersion = "0.2.0"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("io.temporal:temporal-spring-boot-starter:1.25.2")
    implementation("org.projectlombok:lombok:$lombokVersion")
    implementation("org.mapstruct:mapstruct:$mapstructVersion")
    runtimeOnly("org.postgresql:postgresql")
    compileOnly("org.projectlombok:lombok:$lombokVersion")
    annotationProcessor("org.projectlombok:lombok:$lombokVersion")
    annotationProcessor("org.mapstruct:mapstruct-processor:$mapstructVersion")
    annotationProcessor("org.projectlombok:lombok-mapstruct-binding:$lombokMapstructBindingVersion")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
