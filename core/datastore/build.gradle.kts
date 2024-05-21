plugins {
    alias(libs.plugins.modernarchitercture.android.library)
    alias(libs.plugins.modernarchitercture.android.library.jacoco)
    alias(libs.plugins.modernarchitercture.android.hilt)
    alias(libs.plugins.modernarchitercture.android.room)
}

android {
    defaultConfig {
        testInstrumentationRunner =
            "com.google.samples.apps.modernarchitercture.core.testing.NiaTestRunner"
    }
    namespace = "com.google.samples.apps.modernarchitercture.core.database"
}

dependencies {
    api(libs.androidx.dataStore.core)
    api(projects.core.datastoreProto)
    api(projects.core.model)

    implementation(projects.core.common)

    testImplementation(projects.core.datastoreTest)
    testImplementation(libs.kotlinx.coroutines.test)
}