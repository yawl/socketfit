plugins {
    kotlin("jvm")
    `java-gradle-plugin`
}

group = "com.convention.build-logic"

dependencies {
    implementation(projects.gradleExtension)
    implementation(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        create("convention.kotlin-jvm") {
            id = "convention.kotlin-jvm"
            implementationClass = "com.convention.KotlinJvmPlugin"
        }
    }
}
