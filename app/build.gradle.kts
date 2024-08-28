import java.io.FileInputStream
import java.util.Properties

plugins {
    kotlin("kapt")
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.jetbrainsKotlinParcelize)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.android.boilerplate"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.android.boilerplate"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    val keystoreProperties = Properties()
    val keystorePropertiesFile = rootProject.file("keystore.properties")
    if (keystorePropertiesFile.canRead()) {
        keystoreProperties.load(FileInputStream(keystorePropertiesFile))
    }
    signingConfigs {
        create("release") {
            storeFile = file(keystoreProperties["release.store.file"] as String)
            storePassword = keystoreProperties["release.store.password"] as String
            keyAlias = keystoreProperties["release.key.alias"] as String
            keyPassword = keystoreProperties["release.key.password"] as String
        }
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("release")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    val gradleProperties = Properties()
    val gradlePropertiesFile = rootProject.file("gradle.properties")
    if (gradlePropertiesFile.canRead()) {
        gradleProperties.load(FileInputStream(gradlePropertiesFile))
    }
    flavorDimensions += listOf("env")
    productFlavors {
        create("stag") {
            dimension = "env"
            applicationIdSuffix = ".stag"
            buildConfigField(
                type = "String",
                name = "BASE_URL",
                value = gradleProperties["base.url.debug"] as String
            )
        }
        create("prod") {
            dimension = "env"
            buildConfigField(
                type = "String",
                name = "BASE_URL",
                value = gradleProperties["base.url.release"] as String
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.10"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    // splashscreen: used for splashscreen
    implementation(libs.androidx.core.splashscreen)

    // navigation: used for navigation between composable
    implementation(libs.androidx.navigation.compose)

    // room: used for persisting data in local database
    implementation(libs.androidx.room)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)

    // work manager: used for scheduling background jobs
    implementation(libs.androidx.work.manager)

    // hilt: dependency injection framework
    implementation(libs.hilt)
    kapt(libs.hilt.compiler)

    // gson: used for serialization/deserialization of kotlin/java objects into JSON and back
    implementation(libs.gson)

    // retrofit: a type-safe http client built on top of the okhttp3
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)

    // okhttp3-logging-interceptor: a network logging interceptor
    implementation(libs.okhttp3.logging.interceptor)

    // coil: image loading library
    implementation(libs.coil.compose)

    // accompanist-Permissions: used for Android runtime permissions support in Jetpack Compose
    implementation(libs.accompanist.permissions)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}