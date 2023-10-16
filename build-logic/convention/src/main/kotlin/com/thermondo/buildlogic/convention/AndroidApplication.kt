package com.thermondo.buildlogic.convention

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

        packaging {
            /**
             * (kotlinx-datetime-jvm and atomicfu-jvm)
             * both containing a file with the same name :
             * META-INF/versions/9/previous-compilation-data.bin.
             */
            resources.excludes.add("META-INF/versions/9/previous-compilation-data.bin")
        }
    }
}
