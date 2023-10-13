package com.rodrigoguerrero.buildlogic.convention

import com.android.build.api.dsl.CommonExtension
import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.file.DuplicatesStrategy
import org.gradle.api.plugins.ExtensionAware
import org.gradle.jvm.tasks.Jar
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

internal fun Project.configureDetekt(
    kotlinJvmProjectExtension: KotlinJvmProjectExtension,
) {
    kotlinJvmProjectExtension.apply {
        configureDetektOptions()
    }
    configureDetektTasks()
}

internal fun Project.configureDetekt(
    commonExtension: CommonExtension<*, *, *, *, *>,
) {
    commonExtension.apply {
        configureDetektOptions()
    }
    configureDetektTasks()
}

private fun Project.configureDetektTasks() {
    tasks.withType<Detekt>().configureEach {
        reports {
            xml.required.set(false)
            html.required.set(true)
            txt.required.set(false)
            sarif.required.set(false)
            md.required.set(false)
        }
    }

    tasks.withType<Jar> {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }
}

private fun Project.configureDetektOptions() {
    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
    val version = libs.findVersion("io.gitlab.arturbosch.detekt.version").get().toString()

    detektOptions {
        toolVersion = version
        allRules = true
        buildUponDefaultConfig = true
        source = files("$projectDir")
        config = files("$rootDir/analysis/detekt/detekt.yml")
        reportsDir = file("${rootProject.rootDir}/app/build/reports/detekt/")
        parallel = true
    }
    dependencies.add("detektPlugins", libs.findLibrary("io.gitlab.arturbosch.detekt.detekt.cli").get())
    dependencies.add("detektPlugins", libs.findLibrary("io.gitlab.arturbosch.detekt.detekt.formatting").get())
}

private fun Project.detektOptions(block: Action<DetektExtension>): Unit =
    (this as ExtensionAware).extensions.configure("detekt", block)
