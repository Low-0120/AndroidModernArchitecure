plugins {
    alias(libs.plugins.modernarchitercture.android.library)
    alias(libs.plugins.modernarchitercture.android.library.jacoco)
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.google.samples.apps.modernarchitercture.core.domain"
}

dependencies {
    api(projects.core.data)
    api(projects.core.model)

    implementation(libs.javax.inject)
    implementation(libs.androidx.test.ext)

    testImplementation(projects.core.testing)

}