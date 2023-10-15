@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.thermondo.android.library)
    alias(libs.plugins.thermondo.android.library.jacoco)
    alias(libs.plugins.thermondo.android.hilt)
    alias(libs.plugins.org.jetbrains.kotlin.plugin.serialization)
    alias(libs.plugins.gradleSecretsPlugin)

}

android {
    namespace = "com.thermondo.network"
    buildFeatures {
        buildConfig = true
    }
}

secrets {
    defaultPropertiesFileName = "secrets.defaults.properties"
}

dependencies {

    implementation(project(":core:model"))

    implementation(libs.io.coil.kt.coil.compose)
    implementation(libs.org.jetbrains.kotlinx.coroutines.android)
    implementation(libs.org.jetbrains.kotlinx.datetime)
    implementation(libs.org.jetbrains.kotlinx.serialization.json)

    api(libs.io.ktor.client.android)
    api(libs.io.ktor.client.core)
    api(libs.io.ktor.client.logging)
    api(libs.io.ktor.client.content.negotiation)
    api(libs.io.ktor.serialization.kotlinx.json)
}