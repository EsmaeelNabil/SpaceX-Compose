import com.android.build.gradle.LibraryExtension
import com.rodrigoguerrero.buildlogic.convention.configureAndroidCompose
import com.rodrigoguerrero.buildlogic.convention.configureDetekt
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibraryComposeConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with (target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("io.gitlab.arturbosch.detekt")
            }

            extensions.configure<LibraryExtension> {
                configureAndroidCompose(this)
                configureDetekt(this)
            }
        }
    }
}
