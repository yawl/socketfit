plugins {
    kotlin("jvm")
}

group = "com.convention.build-logic"

dependencies {
    compileOnly(gradleApi())
    api(files((libs as Any).javaClass.superclass.protectionDomain.codeSource.location))
}
