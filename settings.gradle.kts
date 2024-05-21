pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "modern-architecture-template"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(":app")
include(":core")
include(":core:data")
include(":core:analytics")
include(":core:designsystem")
include(":core:domain")
include(":core:model")
include(":core:common")
include(":core:data-test")
include(":core:database")
include(":core:datastore-proto")
include(":core:datastore-test")
include(":core:datastore")
include(":core:network")
include(":core:notifications")
include(":core:screenshot-testing")
include(":core:testing")
include(":core:ui")
