import com.android.build.gradle.LibraryExtension
import com.rodrigoguerrero.buildlogic.convention.configureAndroidRoom
import com.rodrigoguerrero.buildlogic.convention.configureDetekt
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidRoomLibraryConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with (target) {
            with (pluginManager) {
                apply("org.jetbrains.kotlin.kapt")
                apply("io.gitlab.arturbosch.detekt")
            }

            extensions.configure<LibraryExtension> {
                configureAndroidRoom(this)
                configureDetekt(this)
            }
        }
    }
}
