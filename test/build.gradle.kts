plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "com.example.composeapp.test"

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
}

dependencies {
    implementation(libs.junit)
    implementation(libs.mockk)
    implementation(libs.kotlin.coroutine.test)
}

kotlin {
    jvmToolchain(17)
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}
