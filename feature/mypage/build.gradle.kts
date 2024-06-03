plugins{
    alias(libs.plugins.modernarchitercture.android.feature)
    alias(libs.plugins.modernarchitercture.android.library.compose)
    alias(libs.plugins.modernarchitercture.android.library.jacoco)
}
android{
    namespace = "com.google.samples.apps.modernarchitercture.feature.mypage"
}

dependencies{
    implementation(projects.core.data)
    implementation(projects.core.domain)

    testImplementation(projects.core.testing)

    androidTestImplementation(projects.core.testing)
}