plugins {
    alias(libs.plugins.modernarchitercture.android.library)
    alias(libs.plugins.modernarchitercture.android.hilt)
}

android {
    namespace = "com.google.samples.apps.modernarchitercture.core.notifications"
}

dependencies {
    api(projects.core.model)

    implementation(projects.core.common)

    compileOnly(platform(libs.androidx.compose.bom))
    compileOnly(libs.androidx.compose.runtime)
}