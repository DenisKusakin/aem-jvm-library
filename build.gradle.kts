plugins {
    kotlin("jvm") version "1.2.31"
}

repositories {
    jcenter()
}

val jcrVersion = "2.4.0"

dependencies {
    implementation(kotlin("stdlib", "1.2.31"))
    implementation("org.apache.jackrabbit:jackrabbit-jcr-client:$jcrVersion")
    implementation("org.apache.jackrabbit:jackrabbit-jcr2dav:$jcrVersion")
    implementation("org.apache.jackrabbit:jackrabbit-spi2dav:$jcrVersion")
    implementation("org.apache.jackrabbit:jackrabbit-jcr2spi:$jcrVersion")
    implementation("javax.jcr:jcr:2.0")
}