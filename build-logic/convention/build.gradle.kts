plugins {
    `kotlin-dsl`
}

group = "com.rodrigoguerrero.buildconvention"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    compileOnly(libs.com.android.tools.build.gradle)
    compileOnly(libs.org.jetbrains.kotlin.gradle.plugin)
    compileOnly(libs.io.gitlab.arturbosch.detekt)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "my.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidComposeApplication") {
            id = "my.android.compose.application"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidLibrary") {
            id = "my.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "my.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("androidTestLibrary") {
            id = "my.android.library.test"
            implementationClass = "AndroidLibraryTestConventionPlugin"
        }
        register("androidFeature") {
            id = "my.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }
        register("androidHilt") {
            id = "my.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }
        register("kotlinLibrary") {
            id = "my.kotlin.library"
            implementationClass = "KotlinLibraryConventionPlugin"
        }
        register("kotlinTestLibrary") {
            id = "my.kotlin.library.test"
            implementationClass = "KotlinLibraryTestConventionPlugin"
        }
        register("androidRoomLibrary") {
            id = "my.android.room.library"
            implementationClass = "AndroidRoomLibraryConventionPlugin"
        }
        register("androidJacocoApplication") {
            id = "my.android.jacoco.application"
            implementationClass = "AndroidApplicationJacocoConventionPlugin"
        }
        register("androidJacocoLibrary") {
            id = "my.android.jacoco.library"
            implementationClass = "AndroidLibraryJacocoConventionPlugin"
        }
    }
}
