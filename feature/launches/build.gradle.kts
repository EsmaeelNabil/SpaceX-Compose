@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.thermondo.android.feature)
    alias(libs.plugins.thermondo.android.library.compose)
    alias(libs.plugins.thermondo.android.library.test)
    alias(libs.plugins.thermondo.android.library.jacoco)
}

android {
    namespace = "com.thermondo.launches"
}
