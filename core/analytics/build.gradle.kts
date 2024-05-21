plugins {
    alias(libs.plugins.modernarchitercture.android.library)
    alias(libs.plugins.modernarchitercture.android.library.compose)
    alias(libs.plugins.modernarchitercture.android.hilt)
}

android {
    namespace = "com.google.samples.apps.modernarchitercture.core.analytics"
}

dependencies {
    implementation(libs.androidx.compose.runtime)
    prodImplementation(platform(libs.firebase.bom))
    prodImplementation(libs.firebase.analytics)
}