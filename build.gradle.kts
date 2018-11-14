import com.sun.org.apache.bcel.internal.Repository

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
    implementation("com.google.code.gson:gson:2.8.5")
    implementation("com.github.kittinunf.fuel:fuel:1.12.1")
    testImplementation("junit:junit:4.12")

    /**
     * compile group: 'com.google.code.gson', name: 'gson', version: '2.8.5'
       compile group: 'com.github.kittinunf.fuel', name: 'fuel', version: '1.12.1'
     */
//    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.1.0")
//    testImplementation("org.junit.jupiter:junit-jupiter-api:5.1.0")
}