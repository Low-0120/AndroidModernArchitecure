plugins {
    alias(libs.plugins.modernarchitercture.android.library)
    alias(libs.plugins.modernarchitercture.android.library.jacoco)
    alias(libs.plugins.modernarchitercture.android.hilt)
}

android {
    namespace = "com.google.samples.apps.modernarchitercture.core.common"
}

dependencies {
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.turbine)
}