import com.android.build.gradle.LibraryExtension
import com.rodrigoguerrero.buildlogic.convention.AppConfig
import com.rodrigoguerrero.buildlogic.convention.configureAndroidLint
import com.rodrigoguerrero.buildlogic.convention.configureDetekt
import com.rodrigoguerrero.buildlogic.convention.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidLibraryConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with (target) {
            with (pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
                apply("io.gitlab.arturbosch.detekt")
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                configureAndroidLint(this)
                configureDetekt(this)
                defaultConfig.targetSdk = AppConfig.targetSdk
            }

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

            dependencies {
                add("implementation", libs.findLibrary("androidx.core.ktx").get())
            }
        }
    }
}
