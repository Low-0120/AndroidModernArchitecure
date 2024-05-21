import com.example.modern_architecture_template.BaseBuildType

plugins {
    alias(libs.plugins.modernarchitercture.android.application)
    alias(libs.plugins.modernarchitercture.android.application.compose)
    alias(libs.plugins.modernarchitercture.android.application.flavors)
    alias(libs.plugins.modernarchitercture.android.application.jacoco)
    alias(libs.plugins.modernarchitercture.android.hilt)
//    alias(libs.plugins.modernarchitercture.android.application.firebase)
    id("com.google.android.gms.oss-licenses-plugin")
    alias(libs.plugins.baselineprofile)
    alias(libs.plugins.roborazzi)
}

android {
    namespace = "com.example.modern_architecture_template"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.modern_architecture_template"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            applicationIdSuffix = BaseBuildType.DEBUG.applicationIdSuffix

        }
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            baselineProfile.automaticGenerationDuringBuild = true
        }
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    //TODO:: feature impl

    //TODO:: core impl
    implementation(projects.core.common)
    implementation(projects.core.ui)
    implementation(projects.core.designsystem)
    implementation(projects.core.data)
    implementation(projects.core.model)
    implementation(projects.core.analytics)
//    implementation(projects.sync.work)

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3.adaptive)
    implementation(libs.androidx.compose.material3.adaptive.layout)
    implementation(libs.androidx.compose.material3.adaptive.navigation)
    implementation(libs.androidx.compose.material3.windowSizeClass)
    implementation(libs.androidx.compose.runtime.tracing)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.profileinstaller)
    implementation(libs.androidx.tracing.ktx)
    implementation(libs.androidx.window.core)
    implementation(libs.kotlinx.coroutines.guava)
    implementation(libs.coil.kt)
    ksp(libs.hilt.compiler)
    kspTest(libs.hilt.compiler)
    testImplementation(libs.androidx.compose.ui.test)
    testImplementation(libs.androidx.work.testing)
    testImplementation(libs.hilt.android.testing)
    debugImplementation(libs.androidx.compose.ui.testManifest)
    testDemoImplementation(libs.robolectric)
    testDemoImplementation(libs.roborazzi)
    androidTestImplementation(libs.androidx.test.espresso.core)
    androidTestImplementation(libs.androidx.navigation.testing)
    androidTestImplementation(libs.androidx.compose.ui.test)
    androidTestImplementation(libs.hilt.android.testing)

    ksp("com.google.devtools.ksp:symbol-processing-api:1.8.10-1.0.0")

}
