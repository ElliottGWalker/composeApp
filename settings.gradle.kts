import com.android.build.api.dsl.SettingsExtension

pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    id("com.android.settings") version "8.3.2"
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

configure<SettingsExtension> {
    compileSdk = 34
    minSdk = 26
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "composeApp"
include(":app")
include(":core:network")
include(":product:data")
include(":product:feature")
include(":test")
include(":test:compose")
include(":ui:components")
include(":ui:theming")
