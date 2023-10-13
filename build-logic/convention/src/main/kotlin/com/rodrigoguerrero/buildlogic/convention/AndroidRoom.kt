package com.rodrigoguerrero.buildlogic.convention

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

internal fun Project.configureAndroidRoom(
    commonExtension: CommonExtension<*, *, *, *, *>,
) {
    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
    commonExtension.apply {

        dependencies {
            add("implementation", libs.findBundle("room").get())
            add("kapt", libs.findLibrary("androidx.room.compiler").get())
            add("testImplementation", libs.findLibrary("androidx.room.testing").get())
        }
    }
}
