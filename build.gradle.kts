plugins {
	java
	id("org.springframework.boot") version "3.4.2"
	id("io.spring.dependency-management") version "1.1.7"
	id ("org.flywaydb.flyway") version "10.20.1"
}


group = "br.com.dio"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}

	flyway {
		url = "jdbc:postgresql://localhost:5432/barber-shop-api"   // URL de conexão com o banco de dados
		user = "barber-shop-api"                                     // Usuário do banco de dados
		password = "barber-shop-api"                                 // Senha do banco de dados
		locations =
            listOf("classpath:db/migration").toTypedArray()                     // Local onde as migrações SQL estão localizadas
		schemas = listOf("public").toTypedArray()                                         // O esquema do banco de dados
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

var mapStructVersion = "1.6.3"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.flywaydb:flyway-core")
	implementation("org.flywaydb:flyway-database-postgresql")
	implementation("org.mapstruct:mapstruct:$mapStructVersion")

	compileOnly("org.projectlombok:lombok")

	developmentOnly("org.springframework.boot:spring-boot-devtools")

	runtimeOnly("org.postgresql:postgresql")

	annotationProcessor("org.mapstruct:mapstruct-processor:$mapStructVersion")
	annotationProcessor("org.projectlombok:lombok-mapstruct-binding:0.2.0")
	annotationProcessor("org.projectlombok:lombok")

	testImplementation("org.springframework.boot:spring-boot-starter-test")

	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
