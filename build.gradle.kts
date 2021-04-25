import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	kotlin("jvm") version "1.4.32"
	id("org.openjfx.javafxplugin") version "0.0.9"
	
}

group = "me.thoma"
version = "1.0-SNAPSHOT"

repositories {
	mavenCentral()
}

dependencies {
	testImplementation(kotlin("test-junit"))
	implementation("no.tornado:tornadofx:1.7.20")
	
}

javafx {
	modules("javafx.controls", "javafx.fxml")
}

tasks.test {
	useJUnit()
}

tasks.withType<KotlinCompile>() {
	kotlinOptions.jvmTarget = "1.8"
}