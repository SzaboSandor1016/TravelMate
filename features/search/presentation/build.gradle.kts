plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    id("kotlin-parcelize")
    /*dependency injection plugin*/
}

android {
    namespace = "com.example.features.search.presentation"
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

    implementation(project(":core:ui"))
    implementation(project(":core:utils"))
    implementation(project(":appnavigation"))

    implementation(project(":features:search:domain"))
    implementation(project(":features:selectedplace:domain"))
    implementation(project(":features:route:domain"))

    implementation(project(":features:savetrip:domain"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    val nav_version = "2.9.3"

    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)

    val fragment_version = "1.8.6"
    // Kotlin
    implementation(libs.androidx.fragment)

    implementation(libs.io.insert.koin)
}