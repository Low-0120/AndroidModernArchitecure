plugins {
    alias(libs.plugins.modernarchitercture.android.library)
    alias(libs.plugins.modernarchitercture.android.library.jacoco)
    alias(libs.plugins.modernarchitercture.android.hilt)
    alias(libs.plugins.modernarchitercture.android.room)
}

android {
//    defaultConfig {
//        testInstrumentationRunner =
//            "com.google.samples.apps.modernarchitercture.core.testing.NiaTestRunner"
//    }
    namespace = "com.google.samples.apps.modernarchitercture.core.database"
}

dependencies {
    api(projects.core.model)
    implementation(libs.kotlinx.datetime)
    androidTestImplementation(projects.core.testing)

}