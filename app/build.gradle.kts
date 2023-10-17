@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.thermondo.android.application)
    alias(libs.plugins.thermondo.android.application.compose)
    alias(libs.plugins.thermondo.android.hilt)
    alias(libs.plugins.thermondo.android.application.jacoco)
    alias(libs.plugins.thermondo.kotlin.library.test)
}

android {
    namespace = "com.thermondo.androidchallenge"

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":core:designsystem"))
    implementation(project(":core:model"))
    implementation(project(":core:data"))
    implementation(project(":core:domain"))
    implementation(project(":core:common"))
    implementation(project(":core:network"))
    implementation(project(":core:database"))

    implementation(project(":feature:launches"))
    implementation(project(":feature:launch-details"))

}