import org.jetbrains.kotlin.gradle.tasks.KotlinCompile // For `KotlinCompile` task below

plugins {
    id("org.springframework.boot") version "3.1.0"
    id("io.spring.dependency-management") version "1.1.0"
    id("org.openapi.generator") version "6.3.0"
    kotlin("jvm") version "1.8.21" // The version of Kotlin to use
    kotlin("plugin.spring") version "1.8.21" // The Kotlin Spring plugin
    //kotlin("plugin.jpa") version "1.8.21"
}

group = "com.nordenms"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-gradle-plugin:3.1.0")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    // Database
    //implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
    //implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    //implementation("org.springframework.boot:spring-boot-starter-jdbc")
    //implementation("org.flywaydb:flyway-core")
    //implementation("org.flywaydb:flyway-mysql")
    //runtimeOnly("org.mariadb.jdbc:mariadb-java-client")

    // Swagger
    implementation("io.springfox:springfox-swagger-ui:3.0.0")
    implementation("io.springfox:springfox-swagger2:3.0.0")
    implementation("org.springdoc:springdoc-openapi-ui:1.6.15")
    implementation("org.springdoc:springdoc-openapi-data-rest:1.6.15")
    implementation("org.springdoc:springdoc-openapi-kotlin:1.6.15")

    // OpenApi generator
    implementation("io.swagger.core.v3:swagger-annotations:2.2.10")
    implementation("io.swagger.core.v3:swagger-models:2.2.10")
    implementation("jakarta.annotation:jakarta.annotation-api:2.1.1")
    compileOnly("jakarta.servlet:jakarta.servlet-api:6.0.0")
    implementation("jakarta.validation:jakarta.validation-api:3.0.2")

    // Test
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> { // Settings for `KotlinCompile` tasks
    kotlinOptions { // Kotlin compiler options
        freeCompilerArgs = listOf("-Xjsr305=strict") // `-Xjsr305=strict` enables the strict mode for JSR-305 annotations
        jvmTarget = "17" // This option specifies the target version of the generated JVM bytecode
    }
    dependsOn(tasks.openApiGenerate)
}

tasks.withType<Test> {
    useJUnitPlatform()
}

val oasSpecLocation = "$rootDir/src/main/resources/openapi.yaml"
val oasGenOutputDir = "$buildDir/generated"
val oasPackage = "$group.to_do.oas"

// Add the generated sources to your project
kotlin.sourceSets["main"].kotlin.srcDir("$oasGenOutputDir/src/main/kotlin")

openApiGenerate {
    inputSpec.set(oasSpecLocation)
    outputDir.set(oasGenOutputDir)
    modelPackage.set("$oasPackage.model")
    apiPackage.set("$oasPackage.api")
    packageName.set(oasPackage)
    generatorName.set("kotlin-spring")
    configOptions.set(
        mapOf(
            "useSpringBoot3" to "true",
            "dateLibrary" to "java8",
            "interfaceOnly" to "true",
            "useTags" to "true"
        )
    )
}

//dependencies {
//    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
//    implementation("org.springframework.boot:spring-boot-starter-web")
//    implementation("com.fasterxml.jackson.module:jackson-module-kotlin") // Jackson extensions for Kotlin for working with JSON
//    implementation("org.jetbrains.kotlin:kotlin-reflect") // Kotlin reflection library, required for working with Spring
//    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8") // Kotlin standard library
//
//    // https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-gradle-plugin
//    implementation("org.springframework.boot:spring-boot-gradle-plugin:3.1.0")
//    // https://mvnrepository.com/artifact/org.springframework/spring-core
//    implementation("org.springframework:spring-core:6.0.9")
//    // https://mvnrepository.com/artifact/org.springframework/spring-web
//    implementation("org.springframework:spring-web:6.0.9")
//    // https://mvnrepository.com/artifact/org.springframework/spring-webmvc
//    implementation("org.springframework:spring-webmvc:6.0.9")
//
//    // OpenApi generator dependencies
//
//    // https://mvnrepository.com/artifact/io.swagger.core.v3/swagger-annotations
//    implementation("io.swagger.core.v3:swagger-annotations:2.2.10")
//    // https://mvnrepository.com/artifact/io.swagger.core.v3/swagger-models
//    implementation("io.swagger.core.v3:swagger-models:2.2.10")
//    // https://mvnrepository.com/artifact/jakarta.annotation/jakarta.annotation-api
//    implementation("jakarta.annotation:jakarta.annotation-api:2.1.1")
//    // https://mvnrepository.com/artifact/jakarta.servlet/jakarta.servlet-api
//    compileOnly("jakarta.servlet:jakarta.servlet-api:6.0.0")
//    // https://mvnrepository.com/artifact/jakarta.validation/jakarta.validation-api
//    implementation("jakarta.validation:jakarta.validation-api:3.0.2")
//    implementation("javax.annotation:javax.annotation-api:1.3.2")
//
//    runtimeOnly("com.h2database:h2")
//    testImplementation("org.springframework.boot:spring-boot-starter-test")
//}