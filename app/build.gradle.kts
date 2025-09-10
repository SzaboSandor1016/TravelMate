plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.travel_mate"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.travel_mate"
        minSdk = 29
        targetSdk = 35
        versionCode = 20250816
        versionName = "2025.8.16"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        viewBinding = true
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {

    implementation(project(":core:utils"))
    implementation(project(":core:ui"))
    implementation(project(":core:database"))
    implementation(project(":core:database:dao"))
    implementation(project(":core:database:data"))
    implementation(project(":core:database:domain"))

    implementation(project(":app:location:data"))
    implementation(project(":app:location:domain"))

    implementation(project(":di"))
    implementation(project(":appnavigation"))

    implementation(project(":features:selectedplaceoptions:domain"))

    implementation(project(":features:findcustom:presentation"))
    implementation(project(":features:inspect:presentation"))
    implementation(project(":features:navigation:presentation"))
    implementation(project(":features:search:presentation"))
    implementation(project(":features:route:presentation"))
    implementation(project(":features:selectedplace:presentation"))
    implementation(project(":features:user:presentation"))
    implementation(project(":features:savetrip:presentation"))

    implementation(project(":features:findcustom:domain"))
    implementation(project(":features:inspect:domain"))
    implementation(project(":features:navigation:domain"))
    implementation(project(":features:search:domain"))
    implementation(project(":features:route:domain"))
    implementation(project(":features:selectedplace:domain"))
    implementation(project(":features:savetrip:domain"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)

    implementation(libs.osmdroid.android)
    implementation(libs.android.gif.drawable)

    implementation(libs.androidx.recyclerview)

    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    implementation(libs.androidx.activity)

    api(libs.androidx.navigation.fragment)
    api(libs.androidx.navigation.ui)
    //api("androidx.navigation:navigation-dynamic-features-fragment:$nav_version")

    testImplementation(kotlin("test"))
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    testImplementation(libs.io.mockk)
    androidTestImplementation(libs.io.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.zxing.android.embedded)

    implementation (libs.osmbonuspack)

    implementation(libs.androidx.room.runtime)
    kapt(libs.androidx.room.compiler)
    implementation(libs.androidx.room)

    // Kotlin
    implementation(libs.androidx.fragment)

    implementation(libs.io.insert.koin)
}
