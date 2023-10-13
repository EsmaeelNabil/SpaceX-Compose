import com.android.build.gradle.LibraryExtension
import com.thermondo.buildlogic.convention.configureAndroidRoom
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidRoomLibraryConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with (target) {
            with (pluginManager) {
                apply("org.jetbrains.kotlin.kapt")
            }

            extensions.configure<LibraryExtension> {
                configureAndroidRoom(this)
            }
        }
    }
}
