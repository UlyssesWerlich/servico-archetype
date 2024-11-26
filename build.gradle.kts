plugins {
	java
	jacoco
	`version-catalog`
	alias(bom.plugins.orgSpringframeworkBoot)
	alias(bom.plugins.ioSpringDependencyManagement)
	alias(bom.plugins.orgSonarqube)
}

java {
	sourceCompatibility = JavaVersion.VERSION_21
	targetCompatibility = JavaVersion.VERSION_21
}

group = "br.com.projetos"
version = "0.4.3"
description = name

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

dependencies {

    // SPRING BOOT
    implementation(bom.springboot.starter.web)
    implementation(bom.springboot.starter.actuator)
	implementation(bom.springboot.starter.validation)

	// PAGEABLE
	implementation(bom.springdata.commons)

	// JACKSON / TIMESTAMP
	implementation(bom.jackson.datatype.jsr310)

	// OPENFEIGN
	implementation(bom.springcloud.starter.openfeign)

	// DATABASE
	implementation(bom.mssql.jdbc)
	implementation(bom.springboot.starter.data.jpa)

    // SWAGGER
    implementation(bom.springdoc.openapi.starter.webmvcUi)

    // MONITORING
	implementation(bom.micrometer.registryPrometheus)

	implementation(bom.exemplo.libs)

	// QUERYDSL
	implementation(variantOf(bom.querydsl.jpa) { classifier("jakarta") })
	annotationProcessor(variantOf(bom.querydsl.apt) { classifier("jakarta") })
	annotationProcessor(bom.springboot.starter.data.jpa)

	// LOMBOK
	annotationProcessor(bom.lombok)

	// LIQUIBASE
	runtimeOnly(bom.liquibase.core)

	// TEST
	runtimeOnly(bom.h2)
	testImplementation(bom.springboot.starter.test)
	testImplementation(bom.restAssured)
}

// CONFIG BUILD
project.layout.buildDirectory = layout.projectDirectory.dir("target")

tasks.getByName<Jar>("jar") {
	enabled = false
}

// CONFIG TESTES
tasks.withType<Test> {
	useJUnitPlatform()
	finalizedBy(tasks.jacocoTestReport)
}

// CONFIG JACOCO
tasks.jacocoTestReport {
	reports {
		xml.required = true
	}
	val classDirectoriesTree = fileTree(layout.buildDirectory.dir("classes/java/main")) {
		include("**/services/**")
		include("**/controllers/**")
	}
	classDirectories.setFrom(classDirectoriesTree)
}

// CONFIG SONARQUBE
sonarqube {
	properties {
		property("sonar.projectKey", "$description")
		property("sonar.coverage.jacoco.xmlReportPaths", "target/reports/jacoco/test/jacocoTestReport.xml")
		property("sonar.java.coveragePlugin", "jacoco")
	}
}
