import com.github.jk1.license.render.CsvReportRenderer
import com.github.jk1.license.render.ReportRenderer

plugins {
    kotlin("jvm") version "2.2.10"
    java
    alias(libs.plugins.springBootPlugin) apply true
    alias(libs.plugins.springBootDependenciesManagement) apply true
    alias(libs.plugins.kotlinAllOpen) apply true
    alias(libs.plugins.kotlinSpring) apply true
    alias(libs.plugins.gitVersion) apply true
    alias(libs.plugins.helmRepository) apply true
    alias(libs.plugins.licenceReport) apply true
    `maven-publish`
}

licenseReport {
    renderers = arrayOf<ReportRenderer>(CsvReportRenderer())
}

configure<com.taktik.gradle.plugins.HelmRepositoryPluginExtension> {
    this.chartNameOverride = "freehealth-connector"
}

val gitVersion: String? by project

group = "org.taktik"

version = gitVersion ?: "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

kotlin {
    jvmToolchain(21)
}

val genJaxb by tasks.registering {
    val sourcesDir = layout.buildDirectory.dir("generated-sources/jaxb")
    val classesDir = layout.buildDirectory.dir("classes/jaxb")
    val schema = "src/resources/WSDL/sts_v1.wsdl"

    outputs.dir(classesDir)

    doLast {
        ant.withGroovyBuilder {
            "taskdef"(
                "name" to "xjc",
                "classname" to "com.sun.tools.xjc.XJCTask",
                "classpath" to configurations["jaxb"].asPath
            )
            "mkdir"("dir" to sourcesDir.get().asFile)
            "mkdir"("dir" to classesDir.get().asFile)
            "xjc"(
                "destdir" to sourcesDir.get().asFile,
                "schema" to schema
            ) {
                "arg"("value" to "-wsdl")
                "produces"("dir" to sourcesDir.get().asFile, "includes" to "**/*.java")
            }
            "javac"(
                "destdir" to classesDir.get().asFile,
                "source" to "1.8",
                "target" to "1.8",
                "debug" to true,
                "debugLevel" to "lines,vars,source",
                "classpath" to configurations["jaxb"].asPath
            ) {
                "src"("path" to sourcesDir.get().asFile)
                "include"("name" to "**/*.java")
                "include"("name" to "*.java")
            }
            "copy"("todir" to classesDir.get().asFile) {
                "fileset"("dir" to sourcesDir.get().asFile, "erroronmissingdir" to false) {
                    "exclude"("name" to "**/*.java")
                }
            }
        }
    }
}

configurations {
    create("jaxb")
    create("ktlint")
    all {
        exclude(group = "log4j", module = "log4j")
        exclude(module = "spring-boot-starter-tomcat")
        exclude(group = "org.apache.tomcat")
        exclude(group = "commons-logging", module = "commons-logging")
        exclude(group = "org.bouncycastle", module = "bcprov-jdk15on")
        exclude(group = "org.springframework", module = "spring-jcl")
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-jetty")
    implementation("com.taktik.boot:spring-boot-starter-micrometer:3.4.34-g3238a3228e")
    implementation("org.springframework.boot:spring-boot-starter-jetty")
    implementation("org.springframework.boot:spring-boot-starter-web") {
        exclude(module = "spring-boot-starter-tomcat")
    }
    implementation("org.springframework.boot:spring-boot-starter-websocket") {
        exclude(module = "spring-boot-starter-tomcat")
    }
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.ws:spring-ws-core")
    implementation("org.springframework.ws:spring-ws-security") {
        exclude(group = "org.bouncycastle")
    }
    implementation("org.springframework:spring-orm")

    implementation("com.nimbusds:nimbus-jose-jwt:9.7")
    implementation("com.nimbusds:oauth2-oidc-sdk:9.2.1")

    implementation("jakarta.xml.ws:jakarta.xml.ws-api:4.0.1")
    implementation("jakarta.xml.bind:jakarta.xml.bind-api:4.0.1")
    implementation("jakarta.xml.soap:jakarta.xml.soap-api:3.0.2")
    implementation("jakarta.validation:jakarta.validation-api:3.0.2")

    implementation("com.taktik.boot:spring-boot-starter-hazelcast:2.1-0-g4d5d84ad53") {
        exclude(module = "kotlin-runtime")
    }
    implementation(libs.kotlinStandardLibrary)
    implementation("org.jetbrains.kotlin:kotlin-reflect:2.2.10")
    implementation(libs.bundles.bouncyCastleLibs)
    implementation("be.fedict.commons-eid:commons-eid-client:1.2.0")
    implementation("be.fedict.commons-eid:commons-eid-jca:1.2.0")
    implementation("be.fgov.ehealth.etee:etee-crypto-lib:2.3.0") {
        exclude(group = "org.bouncycastle")
    }
    //runtimeOnly("com.sun.xml.bind:jaxb-impl:4.0.3")
    implementation("org.apache.commons:commons-compress:1.26.0")
    implementation("org.apache.ws.security:wss4j:1.6.18")
    implementation("org.apache.wss4j:wss4j-ws-security-common:2.3.1")
    implementation("org.apache.wss4j:wss4j-ws-security-dom:2.3.1")
    implementation("net.sf.jsignature.io-tools:easystream:1.2.12")
    implementation("uk.org.lidalia:sysout-over-slf4j:1.0.2")
    implementation("org.apache.commons:commons-lang3:3.18.0")
    implementation("org.apache.velocity:velocity:1.7")
    implementation("commons-io:commons-io:2.20.0")
    implementation("commons-codec:commons-codec:1.10")
    implementation("joda-time:joda-time:2.14.0")
    implementation("com.sun.xml.messaging.saaj:saaj-impl:1.3.28")
    implementation("com.sun.xml.wss:xws-security:3.0")
    implementation("com.hazelcast:hazelcast:3.9.4")
    implementation("com.google.code.gson:gson:2.7")
    implementation("net.sf.dozer:dozer:5.5.1")
    implementation("ma.glasnost.orika:orika-core:1.4.6")
    implementation("io.springfox:springfox-swagger2:2.6.1")
    implementation("net.sf.saxon:Saxon-HE:9.9.1-5")
    implementation("org.bitbucket.b_c:jose4j:0.7.2")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.9.8")
    implementation("io.jsonwebtoken:jjwt-api:0.13.0")
    implementation("io.jsonwebtoken:jjwt-impl:0.13.0")
    implementation("io.jsonwebtoken:jjwt-jackson:0.13.0")

    implementation("org.webjars:webjars-locator-core")
    implementation("org.webjars:sockjs-client:1.0.2")
    implementation("org.webjars:stomp-websocket:2.3.3")
    implementation("org.webjars:bootstrap:3.3.7")
    implementation("org.webjars:jquery:3.1.1-1")

    implementation("org.json:json:20211205")

    implementation("org.apache.lucene:lucene-analyzers-common:4.10.4")
    implementation("org.apache.lucene:lucene-core:4.10.4")
    implementation("org.apache.lucene:lucene-highlighter:4.10.4")
    implementation("org.apache.lucene:lucene-memory:4.10.4")
    implementation("org.apache.lucene:lucene-queries:4.10.4")
    implementation("org.apache.lucene:lucene-queryparser:4.10.4")
    implementation("org.apache.lucene:lucene-sandbox:4.10.4")
    implementation("org.apache.lucene:lucene-suggest:4.10.4")

    implementation("org.slf4j:log4j-over-slf4j:1.7.25")
    implementation("org.jdom:jdom-legacy:1.1.3")
    implementation("com.taktik.boot:spring-boot-starter-gke-logging:2.1.174-0f038f8004")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.register<JavaExec>("ktlint") {
    group = "verification"
    description = "Check Kotlin code style."
    classpath = configurations["ktlint"]
    mainClass.set("com.github.shyiko.ktlint.Main")
    args = listOf("src/**/*.kt")
}

tasks.register<JavaExec>("ktlintFormat") {
    group = "formatting"
    description = "Fix Kotlin code style deviations."
    classpath = configurations["ktlint"]
    mainClass.set("com.github.shyiko.ktlint.Main")
    args = listOf("-F", "src/**/*.kt")
}

tasks.withType<Jar> {
    manifest {
        attributes(
            "Built-By" to System.getProperty("user.name"),
            "Build-Revision" to gitVersion,
            "Created-By" to "Gradle ${gradle.gradleVersion}",
            "Build-Jdk" to "${System.getProperty("java.version")} (${System.getProperty("java.vendor")} ${System.getProperty("java.vm.version")})",
            "Build-OS" to "${System.getProperty("os.name")} ${System.getProperty("os.arch")} ${System.getProperty("os.version")}"
        )
    }
}

tasks.withType<org.springframework.boot.gradle.tasks.bundling.BootJar> {
    manifest {
        attributes(
            "Built-By" to System.getProperty("user.name"),
            "Build-Revision" to gitVersion,
            "Created-By" to "Gradle ${gradle.gradleVersion}",
            "Build-Jdk" to "${System.getProperty("java.version")} (${System.getProperty("java.vendor")} ${System.getProperty("java.vm.version")})",
            "Build-OS" to "${System.getProperty("os.name")} ${System.getProperty("os.arch")} ${System.getProperty("os.version")}"
        )
    }
}

extra["name"] = "freehealth-connector"
