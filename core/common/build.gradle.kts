@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.thermondo.android.library)
    alias(libs.plugins.thermondo.android.library.jacoco)
    alias(libs.plugins.org.jetbrains.kotlin.plugin.serialization)
    alias(libs.plugins.thermondo.android.hilt)
}

android {
    namespace = "com.thermondo.common"
}

dependencies {
    implementation(libs.org.jetbrains.kotlinx.serialization.json)
}