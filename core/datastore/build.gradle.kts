plugins {
    alias(libs.plugins.modernarchitercture.android.library)
    alias(libs.plugins.modernarchitercture.android.library.jacoco)
    alias(libs.plugins.modernarchitercture.android.hilt)
}

android {
    defaultConfig {
        consumerProguardFiles("consumer-proguard-rules.pro")
    }
    namespace = "com.google.samples.apps.modernarchitercture.core.datastore"
    testOptions {
        unitTests {
            isReturnDefaultValues = true
        }
    }
}

dependencies {
    api(libs.androidx.dataStore.core)
    api(projects.core.datastoreProto)
    api(projects.core.model)

    implementation(projects.core.common)
    implementation(libs.androidx.test.ext)

    testImplementation(projects.core.datastoreTest)
    testImplementation(libs.kotlinx.coroutines.test)
}