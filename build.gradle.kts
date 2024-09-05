plugins {
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("io.micronaut.application") version "4.4.2"
    id("io.micronaut.test-resources") version "4.4.2"
    id("io.micronaut.aot") version "4.4.2"
}

version = "0.1"
group = "example.atp"

repositories {
    mavenCentral()
}

dependencies {
    annotationProcessor("io.micronaut.data:micronaut-data-processor")
    annotationProcessor("io.micronaut:micronaut-http-validation")
    annotationProcessor("io.micronaut.serde:micronaut-serde-processor")
    annotationProcessor("io.micronaut.validation:micronaut-validation-processor")
    implementation("io.micronaut.data:micronaut-data-jdbc")
    implementation("io.micronaut.serde:micronaut-serde-jackson")
    implementation("io.micronaut.sql:micronaut-jdbc-hikari")
    implementation("io.micronaut.validation:micronaut-validation")
    implementation("jakarta.validation:jakarta.validation-api")
    compileOnly("io.micronaut:micronaut-http-client")
    runtimeOnly("ch.qos.logback:logback-classic")
    runtimeOnly("com.oracle.database.jdbc:ojdbc11")
    testImplementation("io.micronaut:micronaut-http-client")

    //Luch
    // Database
    implementation(platform("com.oracle.database.jdbc:ojdbc-bom:21.13.0.0"))
    runtimeOnly("com.oracle.database.security:oraclepki")
    runtimeOnly("com.oracle.database.security:osdt_cert")
    runtimeOnly("com.oracle.database.security:osdt_core")

    //Flyway
    runtimeOnly("io.micronaut.flyway:micronaut-flyway")
    runtimeOnly("org.flywaydb:flyway-database-oracle")

    //Vault
    runtimeOnly("io.micronaut.oraclecloud:micronaut-oraclecloud-vault")

    // Wallet
    runtimeOnly("io.micronaut.oraclecloud:micronaut-oraclecloud-sdk")
    runtimeOnly("io.micronaut.oraclecloud:micronaut-oraclecloud-atp")
}


application {
    mainClass = "example.atp.Application"
}
java {
    sourceCompatibility = JavaVersion.toVersion("17")
    targetCompatibility = JavaVersion.toVersion("17")
}


graalvmNative.toolchainDetection = false

micronaut {
    runtime("netty")
    testRuntime("junit5")
    processing {
        incremental(true)
        annotations("example.atp.*")
    }
    aot {
        // Please review carefully the optimizations enabled below
        // Check https://micronaut-projects.github.io/micronaut-aot/latest/guide/ for more details
        optimizeServiceLoading = false
        convertYamlToJava = false
        precomputeOperations = true
        cacheEnvironment = true
        optimizeClassLoading = true
        deduceEnvironment = true
        optimizeNetty = true
        replaceLogbackXml = true
    }
    testResources {
        clientTimeout = 360
    }
}



