/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.thermondo.buildlogic.convention

import com.android.build.api.variant.AndroidComponentsExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.register
import org.gradle.testing.jacoco.plugins.JacocoPluginExtension
import org.gradle.testing.jacoco.tasks.JacocoReport
import java.util.Locale

private val exclusions = setOf(
    "**/R.class",
    "**/R\$*.class",
    "**/BuildConfig.*",
    "**/Manifest*.*",
)

internal fun Project.configureJacoco(
    androidComponentsExtension: AndroidComponentsExtension<*, *, *>,
) {
    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
    configure<JacocoPluginExtension> {
        toolVersion = libs.findVersion("org.jacoco.version").get().toString()
    }

    val jacocoTestReport = tasks.create("jacocoTestReport")
    androidComponentsExtension.onVariants { variant ->
        val testTaskName = "test${variant.name.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
        }}UnitTest"

        val reportTask = tasks.register(
            "jacoco${testTaskName.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
            }}Report", JacocoReport::class,
        ) {
            dependsOn(testTaskName)
            reports {
                xml.required.set(false)
                html.required.set(true)
            }

            classDirectories.setFrom(
                fileTree("$buildDir/tmp/kotlin-classes/${variant.name}") {
                    exclude(exclusions)
                },
            )

            sourceDirectories.setFrom(files("$projectDir/src/main/java", "$projectDir/src/main/kotlin"))
            executionData.setFrom(file("$buildDir/jacoco/$testTaskName.exec"))
        }

        jacocoTestReport.dependsOn(reportTask)
    }
}
