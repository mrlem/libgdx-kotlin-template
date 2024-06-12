/*
 * This settings file is used to specify which projects to include in your build-logic build.
 */

dependencyResolutionManagement {
    // Reuse version catalog from the main build.
    versionCatalogs {
        create("libs", { from(files("../gradle/libs.versions.toml")) })
    }
}

rootProject.name = "build-logic"
