plugins {
    alias(libs.plugins.modernarchitercture.android.library)
    alias(libs.plugins.modernarchitercture.android.library.compose)
    alias(libs.plugins.modernarchitercture.android.hilt)
}

android {
    namespace = "com.google.samples.apps.modernarchitercture.core.screenshottesting"
}

dependencies {
    api(libs.roborazzi)
    implementation(libs.androidx.compose.ui.test)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.ui.test)
    implementation(libs.robolectric)
    implementation(projects.core.common)
    implementation(projects.core.designsystem)
}