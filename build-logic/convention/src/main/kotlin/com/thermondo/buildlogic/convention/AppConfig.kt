package com.thermondo.buildlogic.convention

import org.gradle.api.JavaVersion

object AppConfig {
    const val applicationId = "com.thermondo.myapp"
    const val compileSdk = 34
    val javaVersion = JavaVersion.VERSION_17
    const val minSdk = 26
    const val targetSdk = compileSdk
    const val versionName = "1.0.0"
    const val versionCode = 1
}
