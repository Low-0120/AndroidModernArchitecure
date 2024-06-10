plugins {
    alias(libs.plugins.modernarchitercture.jvm.library)
    id("kotlinx-serialization")
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
    api(libs.kotlinx.datetime)
}