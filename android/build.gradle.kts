plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

repositories {
    google()
    mavenCentral()
}

android {
    sourceSets {
        getByName("main") {
            manifest.srcFile("AndroidManifest.xml")
            aidl.srcDirs("src")
            renderscript.srcDirs("src")
            res.srcDirs("res")
            assets.srcDirs("assets")
            jniLibs.srcDirs("libs")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    packaging {
        resources.excludes += "META-INF/robovm/ios/robovm.xml"
    }
    defaultConfig {
        namespace = "org.example.sample"
        buildToolsVersion = "35.0.0"
        compileSdk = 35
        minSdk = 29
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android.txt"),
                "proguard-rules.pro",
            )
        }
    }
}

val natives: Configuration by configurations.creating

// Called every time gradle gets executed, takes the native dependencies of
// the natives configuration, and extracts them to the proper libs/ folders
// so they get packed with the APK.
tasks.register("copyAndroidNatives") {
    doFirst {
        natives.files.forEach { jar ->
            val outputDir = file("libs/" + jar.nameWithoutExtension.substringAfterLast("natives-"))
            outputDir.mkdirs()
            copy {
                from(zipTree(jar))
                into(outputDir)
                include("*.so")
            }
        }
    }
}

tasks.whenTaskAdded {
    if ("package" in name) {
        dependsOn("copyAndroidNatives")
    }
}

dependencies {
    implementation(project(":core"))
    api(libs.gdx.backend.android)
    natives("${libs.gdx.platform.get()}:natives-armeabi-v7a")
    natives("${libs.gdx.platform.get()}:natives-arm64-v8a")
    natives("${libs.gdx.platform.get()}:natives-x86")
    natives("${libs.gdx.platform.get()}:natives-x86_64")
}