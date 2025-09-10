import org.jetbrains.kotlin.konan.properties.hasProperty
import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    id("kotlin-parcelize")
}

android {
    namespace = "com.example.core.remotedatasources"
    compileSdk = 35

    defaultConfig {
        minSdk = 29

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        val localPropertiesFile = rootProject.file("local.properties")
        val localProperties = Properties()
        if (localPropertiesFile.exists()) {
            localPropertiesFile.inputStream().use { localProperties.load(it) }
        }
        val apiKey = if(localProperties.hasProperty("OPEN_ROUTE_SERVICE_KEY"))
            localProperties.getProperty("OPEN_ROUTE_SERVICE_KEY") as String
        else System.getenv("OPEN_ROUTE_SERVICE_KEY") ?: ""
        buildConfigField("String", "OPEN_ROUTE_SERVICE_KEY", "\"$apiKey\"")

        /*val apiKey = if(project.hasProperty("OPEN_ROUTE_SERVICE_KEY")) project.property("OPEN_ROUTE_SERVICE_KEY") as String
            else System.getenv("OPEN_ROUTE_SERVICE_KEY") ?: "asd"*/
        //buildConfigField("String", "OPEN_ROUTE_SERVICE_KEY", "\"${apiKey}\"")
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

    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.io.insert.koin)
}