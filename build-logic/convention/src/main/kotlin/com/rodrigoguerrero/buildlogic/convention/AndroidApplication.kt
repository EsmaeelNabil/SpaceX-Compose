package com.rodrigoguerrero.buildlogic.convention

import com.android.build.api.dsl.ApplicationExtension

internal fun configureAndroidApplication(
    applicationExtension: ApplicationExtension
) {
    applicationExtension.apply {
        defaultConfig {
            applicationId = AppConfig.applicationId
            targetSdk = AppConfig.targetSdk
            versionName = AppConfig.versionName
            versionCode = AppConfig.versionCode
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

            vectorDrawables {
                useSupportLibrary = true
            }
        }
    }
}
