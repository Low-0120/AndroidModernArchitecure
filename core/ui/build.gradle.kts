plugins {
    alias(libs.plugins.modernarchitercture.android.library)
    alias(libs.plugins.modernarchitercture.android.library.compose)
    alias(libs.plugins.modernarchitercture.android.library.jacoco)
}

android {
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    namespace = "com.google.samples.apps.modernarchitercture.core.ui"
}

dependencies {
    api(libs.androidx.metrics)
    api(projects.core.analytics)
    api(projects.core.designsystem)
    api(projects.core.model)

    implementation(libs.androidx.browser)
    implementation(libs.coil.kt)
    implementation(libs.coil.kt.compose)

    androidTestImplementation(projects.core.testing)
}