import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.modernarchitercture.android.library)
    alias(libs.plugins.modernarchitercture.android.library.jacoco)
    alias(libs.plugins.modernarchitercture.android.hilt)
    id("kotlinx-serialization")
//    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

android {

    buildTypes {
        getByName("debug") {
            buildConfigField(
                "String",
                "API_BASE_URL",
                "\"${getProperty("secrets.debug.properties", "BASE_URL")}\""
            )
        }
        getByName("release") {
            buildConfigField(
                "String",
                "API_BASE_URL",
                "\"${getProperty("secrets.release.properties", "BASE_URL")}\""
            )
//            isMinifyEnabled = true
//            proguardFiles(
//                getDefaultProguardFile("proguard-android-optimize.txt"),
//                "proguard-rules.pro"
//            )
        }
    }
    buildFeatures {
        buildConfig = true
    }
    namespace = "com.google.samples.apps.modernarchitercture.core.network"
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}
//
//secrets {
//    defaultPropertiesFileName = "secrets.defaults.properties"
//}

dependencies {
    api(libs.kotlinx.datetime)
    api(projects.core.common)
    api(projects.core.model)

    implementation(libs.coil.kt)
    implementation(libs.coil.kt.svg)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.okhttp.logging)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.androidx.test.ext)
    testImplementation(libs.kotlinx.coroutines.test)
}

fun getProperty(fileName: String, propertyName: String): String {
    val properties = Properties()
    properties.load(FileInputStream(rootProject.file(fileName)))
    return properties.getProperty(propertyName)
}