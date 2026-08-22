package com.convention

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class KotlinBaseConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            val kotlinVersion = KotlinVersion.DEFAULT
            tasks.withType(KotlinCompile::class.java).configureEach { kotlin ->
                kotlin.compilerOptions {
                    languageVersion.set(kotlinVersion)
                    apiVersion.set(kotlinVersion)
                }
            }
        }
    }
}
