package com.convention

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

class KotlinJvmBasePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            plugins.apply(libs.plugins.kotlin.jvm.get().pluginId)
            plugins.apply(KotlinBaseConventionPlugin::class.java)

            val javaTarget = JavaLanguageVersion.of(libs.versions.java.get())
            extensions.configure(KotlinJvmProjectExtension::class.java) {
                it.jvmToolchain { java ->
                    java.languageVersion.set(javaTarget)
                }
            }
        }
    }
}
