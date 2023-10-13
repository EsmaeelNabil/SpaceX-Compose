package com.rodrigoguerrero.buildlogic.convention

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.getByType

internal fun Project.configureAndroidLint(
    commonExtension: CommonExtension<*, *, *, *, *>
) {
    commonExtension.apply {
        val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
        dependencies.add("lintChecks", libs.findLibrary("com.slack.lint.compose.lint.checks").get())
        dependencies.add("lintChecks", project(":analysis:lint-custom-rules"))

        androidOptions {
            lint {
                quiet = false
                abortOnError = true
                checkDependencies = true
                ignoreTestSources = true
                warningsAsErrors = true
                htmlReport = true
                textReport = false
                xmlReport = false
                lintConfig = file("${rootDir}/analysis/lint/lint-config.xml")
            }
        }
    }
}

internal fun Project.androidOptions(block: Action<CommonExtension<*, *, *, *, *>>): Unit =
    (this as ExtensionAware).extensions.configure("android", block)
