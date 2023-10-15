@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.thermondo.android.library)
    alias(libs.plugins.thermondo.android.library.jacoco)
    alias(libs.plugins.thermondo.android.hilt)
    alias(libs.plugins.org.jetbrains.kotlin.plugin.serialization)
    alias(libs.plugins.thermondo.android.room)
}

android {
    namespace = "com.thermondo.databse"
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    implementation(project(":core:model"))
    implementation(libs.org.jetbrains.kotlinx.coroutines.android)
    implementation(libs.org.jetbrains.kotlinx.datetime)
    implementation(libs.org.jetbrains.kotlinx.serialization.json)
}