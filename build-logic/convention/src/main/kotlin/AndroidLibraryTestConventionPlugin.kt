import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.kotlin

class AndroidLibraryTestConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with (target) {

            extensions.configure<LibraryExtension> {
                val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

                dependencies {
                    add("androidTestImplementation", kotlin("test"))
                    add("testImplementation", kotlin("test"))
                    add("testImplementation", libs.findBundle("test").get())
                    add("androidTestImplementation", libs.findBundle("android.test").get())
                    add("testImplementation", libs.findLibrary("org.jetbrains.kotlinx.coroutines.test").get())
                }
            }
        }
    }
}
