import com.android.build.gradle.LibraryExtension
import com.rodrigoguerrero.buildlogic.convention.configureDetekt
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidFeatureConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with (target) {
            with(pluginManager) {
                apply("my.android.library")
                apply("my.android.hilt")
                apply("io.gitlab.arturbosch.detekt")
            }
            extensions.configure<LibraryExtension> {
                configureDetekt(this)
            }
        }
    }
}