plugins {
    `kotlin-dsl`
}

group = "com.thermondo.buildconvention"

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
            id = "thermondo.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidComposeApplication") {
            id = "thermondo.android.compose.application"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidLibrary") {
            id = "thermondo.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "thermondo.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("androidTestLibrary") {
            id = "thermondo.android.library.test"
            implementationClass = "AndroidLibraryTestConventionPlugin"
        }
        register("androidFeature") {
            id = "thermondo.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }
        register("androidHilt") {
            id = "thermondo.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }
        register("kotlinLibrary") {
            id = "thermondo.kotlin.library"
            implementationClass = "KotlinLibraryConventionPlugin"
        }
        register("kotlinTestLibrary") {
            id = "thermondo.kotlin.library.test"
            implementationClass = "KotlinLibraryTestConventionPlugin"
        }
        register("androidRoomLibrary") {
            id = "thermondo.android.room.library"
            implementationClass = "AndroidRoomLibraryConventionPlugin"
        }
        register("androidJacocoApplication") {
            id = "thermondo.android.jacoco.application"
            implementationClass = "AndroidApplicationJacocoConventionPlugin"
        }
        register("androidJacocoLibrary") {
            id = "thermondo.android.jacoco.library"
            implementationClass = "AndroidLibraryJacocoConventionPlugin"
        }
    }
}
