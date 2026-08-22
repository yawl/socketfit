@file:Suppress("UnstableApiUsage")

rootProject.name = "socketfit-root"

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

include(":socketfit")

include(":socketfit-converters:kotlinx-serialization")

include(":socketfit-adapters:kotlinx-coroutines")

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
