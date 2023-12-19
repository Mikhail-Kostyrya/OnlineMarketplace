plugins {
    id("java")
    id("org.flywaydb.flyway") version "10.0.1"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":models"))
    implementation(project(":repos"))
    implementation(project(":json_work"))
    implementation("org.postgresql:postgresql:42.6.0")
}

flyway {
    url = "jdbc:postgresql://localhost:5432/marketplace"
    user = "admin"
    password = "admin"
    locations = arrayOf("filesystem:src/main/resources/db/migration")
}