plugins {
    alias(libs.plugins.modernarchitercture.android.library)
    alias(libs.plugins.modernarchitercture.android.hilt)
}

android {
    namespace = "com.google.samples.apps.modernarchitercture.core.data.test"
}

dependencies {
    implementation(libs.hilt.android.testing)
    implementation(projects.core.common)
    implementation(projects.core.datastore)
    implementation(libs.androidx.test.ext)
}