pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "AndroidCodingChallenge"
include(":app")
include(":core:designsystem")
include(":core:model")
include(":core:network")
include(":core:data")
include(":core:database")
include(":core:common")
include(":core:domain")
