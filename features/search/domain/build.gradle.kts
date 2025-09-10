plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    id("kotlin-parcelize")
    /*dependency injection plugin*/
}

android {
    namespace = "com.example.features.search.domain"
    compileSdk = 35

    defaultConfig {
        minSdk = 29

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        buildConfig = true
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(project(":app:location:domain"))
    implementation(project(":core:remotedatasources"))
    implementation(project(":core:remotedatasources:searchstartdatasource:domain"))
    implementation(project(":core:remotedatasources:searchplacesdatasource:domain"))
    implementation(project(":core:remotedatasources:reversegeocode:domain"))

    // Kotlin Coroutines (for Flow/StateFlow, etc.)
    implementation(libs.kotlinx.coroutines.core)
    implementation(project(":core:remotedatasources"))
    implementation(project(":core:remotedatasources"))

    // Optional: for testing domain logic
    testImplementation(kotlin("test"))
    testImplementation(libs.kotlinx.coroutines.test)

    implementation(libs.io.insert.koin)
}