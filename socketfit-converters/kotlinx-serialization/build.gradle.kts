plugins {
    alias(libs.plugins.convention.kotlin.jvm)
    alias(libs.plugins.kotlin.serialization)
}

dependencies {
    api(projects.socketfit)
    api(libs.kotlinx.serialization.core)
}
