// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(libs.com.android.tools.build.gradle)
        classpath(libs.org.jetbrains.kotlin.gradle.plugin)
        classpath(libs.com.google.dagger.hilt.android.gradle.plugin)
        classpath(libs.app.cash.paparazzi.gradle.plugin)
        classpath(libs.org.jacoco.core)
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
