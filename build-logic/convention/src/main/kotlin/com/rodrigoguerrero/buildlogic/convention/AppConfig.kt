package com.rodrigoguerrero.buildlogic.convention

import org.gradle.api.JavaVersion

object AppConfig {
    const val applicationId = "com.rodrigoguerrero.myapp"
    const val compileSdk = 34
    val javaVersion = JavaVersion.VERSION_17
    const val minSdk = 21
    const val targetSdk = compileSdk
    const val versionName = "1.0.0"
    const val versionCode = 1
}
