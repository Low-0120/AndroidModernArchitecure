package com.example.modern_architecture_template

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.ApplicationProductFlavor
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import com.android.build.api.dsl.ProductFlavor
import org.gradle.api.Project
import java.util.Properties

@Suppress("EnumEntryName")
enum class FlavorDimension {
    contentType
}

// The content for the app can either come from local static data which is useful for demo
// purposes, or from a production backend server which supplies up-to-date, real content.
// These two product flavors reflect this behaviour.
@Suppress("EnumEntryName")
enum class BaseFlavor(val dimension: FlavorDimension, val applicationIdSuffix: String? = null) {
    demo(FlavorDimension.contentType, applicationIdSuffix = ".demo"),
    prod(FlavorDimension.contentType)
}

fun configureBuildFeatures(applicationExtension: ApplicationExtension) {
    applicationExtension.buildFeatures.buildConfig = true
}

fun configureFlavors(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
    project: Project,
    flavorConfigurationBlock: ProductFlavor.(flavor: BaseFlavor) -> Unit = {}
) {
    commonExtension.apply {
        flavorDimensions += FlavorDimension.contentType.name
        productFlavors {
            BaseFlavor.values().forEach {
                create(it.name) {
                    dimension = it.dimension.name
                    flavorConfigurationBlock(this, it)
                    if (this@apply is ApplicationExtension && this is ApplicationProductFlavor) {
                        if (it.applicationIdSuffix != null) {
                            applicationIdSuffix = it.applicationIdSuffix
                        }
                        configureBuildConfigFields(this, it, project)
                    }
                }
            }
        }
    }
}
fun configureBuildConfigFields(productFlavor: ProductFlavor, flavor: BaseFlavor, project: Project) {
    val defaultProperties = Properties().apply {
        load(project.rootProject.file("secrets.defaults.properties").inputStream())
    }

    val environmentPropertiesFile = when (flavor) {
        BaseFlavor.demo -> "secrets.debug.properties"
        BaseFlavor.prod -> "secrets.release.properties"
    }

    val environmentProperties = Properties().apply {
        load(project.rootProject.file(environmentPropertiesFile).inputStream())
    }

    defaultProperties.putAll(environmentProperties)

    productFlavor.buildConfigField("String", "API_BASE_URL", "\"${defaultProperties.getProperty("BASE_URL")}\"")}