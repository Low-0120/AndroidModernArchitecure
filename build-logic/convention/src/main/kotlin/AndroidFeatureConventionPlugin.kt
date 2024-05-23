import com.android.build.api.dsl.LibraryExtension
import com.example.modern_architecture_template.configureGradleManagedDevices
import com.example.modern_architecture_template.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.internal.impldep.com.jcraft.jsch.ConfigRepository.defaultConfig
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import java.awt.AWTEventMulticaster.add

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("modernarchitercture.android.library")
                apply("modernarchitercture.android.hilt")
            }
            extensions.configure<LibraryExtension> {
                defaultConfig {
                    testInstrumentationRunner =""//TODO::Core追加時追加
                }
                testOptions.animationsDisabled = true
                configureGradleManagedDevices(this)
            }

            dependencies{

                add("implementation", project(":core:ui"))
                add("implementation", project(":core:designsystem"))

                add("implementation", libs.findLibrary("androidx.hilt.navigation.compose").get())
                add("implementation", libs.findLibrary("androidx.lifecycle.runtimeCompose").get())
                add("implementation", libs.findLibrary("androidx.lifecycle.viewModelCompose").get())
                add("implementation", libs.findLibrary("androidx.tracing.ktx").get())

                add("androidTestImplementation", libs.findLibrary("androidx.lifecycle.runtimeTesting").get())
            }
        }
    }
}