package com.thermondo.buildlogic.convention

import org.gradle.api.Project
import com.android.build.api.dsl.CommonExtension
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, * ,*, *, *>,
) {
    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
    commonExtension.apply {
        buildFeatures {
            compose = true
        }

        composeOptions {
            kotlinCompilerExtensionVersion = libs.findVersion("androidx-compose-compiler-version").get().toString()
        }

        dependencies {
            val bom = libs.findLibrary("androidx-compose-bom").get()
            add("implementation", platform(bom))
            add("androidTestImplementation", platform(bom))
            add("implementation", libs.findLibrary("androidx.activity.compose").get())
            add("implementation", libs.findLibrary("io-coil-kt-coil-compose").get())
            add("implementation", libs.findLibrary("androidx.compose.ui").get())
            add("implementation", libs.findLibrary("androidx.lifecycle.runtime.compose").get())
            add("implementation", libs.findLibrary("androidx-hilt-navigation-compose").get())
            add("implementation", libs.findLibrary("androidx.compose.material3").get())
            add("implementation", libs.findLibrary("androidx.compose.material-icons-extended").get())
            add("implementation", libs.findLibrary("androidx.compose.ui.tooling.preview").get())

            add("implementation", libs.findLibrary("androidx.paging.runtime").get())
            add("implementation", libs.findLibrary("androidx.paging.compose").get())

            add("debugImplementation", libs.findLibrary("androidx.compose.ui.tooling").get())
            add("implementation", libs.findBundle("accompanist").get())
        }
    }
}
