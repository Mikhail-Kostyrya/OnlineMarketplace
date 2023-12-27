plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":models"))
    implementation(project(":repos"))
    implementation(project(":repos_sql"))

    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.1")
    implementation("javax.servlet:javax.servlet-api:4.0.1")
    implementation("org.apache.tomcat.embed:tomcat-embed-core:9.0.73")
    implementation("org.apache.tomcat:tomcat-jasper:9.0.73")
    implementation("org.apache.tomcat:tomcat-catalina:9.0.73")
}