plugins {
    alias(libs.plugins.thermondo.android.library)
    alias(libs.plugins.thermondo.android.library.compose)
}

android {
    namespace = "com.thermondo.designsystem"
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:common"))
}