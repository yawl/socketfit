plugins {
    alias(libs.plugins.convention.kotlin.jvm)
}

dependencies {
    api(projects.socketfit)
    api(libs.gson)
}
