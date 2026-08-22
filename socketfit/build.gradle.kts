plugins {
    alias(libs.plugins.convention.kotlin.jvm)
}

dependencies {
    api(libs.okhttp)
    implementation(libs.kotlinx.coroutines.core)
}
