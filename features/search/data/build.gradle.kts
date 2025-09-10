plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    id("kotlin-parcelize")
}

android {
    namespace = "com.example.features.search.data"
    compileSdk = 35

    defaultConfig {
        minSdk = 29

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        buildConfig = true
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

    implementation(project(":core:remotedatasources"))

    implementation(project(":core:remotedatasources:searchstartdatasource:domain"))
    implementation(project(":core:remotedatasources:searchplacesdatasource:domain"))

    implementation(project(":features:search:domain"))

    implementation(libs.androidx.lifecycle.livedata.ktx)

    implementation(libs.io.insert.koin)
}