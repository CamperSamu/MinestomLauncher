import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

group	=	"com.thecrownstudios"
version	=	"1.2"

plugins {
	id("java")
	id("com.github.johnrengelman.shadow") version "7.1.2"
}

java {
	sourceCompatibility = JavaVersion.VERSION_17
	targetCompatibility = JavaVersion.VERSION_17

	tasks.withType<JavaCompile> {
		options.encoding = "UTF-8"
	}

	tasks.withType<Javadoc> {
		options.encoding = "UTF-8"
	}

	tasks.withType<Jar> {
		manifest {
			attributes["Main-Class"] = "com.thecrownstudios.minestomlauncher.Main"
		}
	}
}

shadow {
	tasks.withType<ShadowJar> {
		exclude("server.json")

		println(message = "SHADOWJAR INFORMATION")
		println(message = "- project_name:     ${rootProject.name}")
		println(message = "- module_name:      ${archiveBaseName.get()}")
		println(message = "- module_version:   ${archiveVersion.get()}")
		println(message = "- module_extension: ${archiveExtension.get()}")
		println()

		//archiveFileName.set("minestom.${archiveExtension.get()}")
		archiveFileName.set("${rootProject.name}-${archiveVersion.get()}.${archiveExtension.get()}")
	}
}

repositories {
	mavenCentral()

	maven(url = "https://repo.spongepowered.org/maven")
	maven(url = "https://libraries.minecraft.net")
	maven(url = "https://jitpack.io")
}

dependencies {
	val minestom_version    = project.properties["minestom_version"]    ?: "c995f9c3a9"
	val jnoise_version      = project.properties["jnoise_version"]      ?: "b93008e35e"
	val minimessage_version = project.properties["minimessage_version"] ?: "4.11.0"

	implementation("com.github.Minestom:Minestom:$minestom_version")
	implementation("com.github.Articdive:JNoise:$jnoise_version")
	implementation("com.github.CatDevz:SlimeLoader:master-SNAPSHOT")
	implementation("net.kyori:adventure-text-minimessage:$minimessage_version")
	implementation("com.fasterxml.jackson.core:jackson-databind:2.14.1")
}