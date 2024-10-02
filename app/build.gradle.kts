plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

val tomtomApiKey : String by project

android {
    namespace = "com.savenko.tomtomnavigationapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.savenko.tomtomnavigationapp"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        jniLibs.pickFirsts.add("lib/**/libc++_shared.so")
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes.configureEach {
        buildConfigField("String", "TOMTOM_API_KEY", "\"$tomtomApiKey\"")
    }
}

dependencies {
    // Basic dependencies
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.fragment)


    // Compose
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.material3)
    androidTestImplementation(platform(libs.androidx.compose.bom))

    // Testing
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    testImplementation(libs.junit)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // TomTom
    implementation(libs.tomtom.navigation.online)
    implementation(libs.tomtom.map.display)
    implementation(libs.tomtom.provider.android)
    implementation(libs.tomtom.provider.map.matched)
    implementation(libs.tomtom.provider.simulation)
    implementation(libs.tomtom.navigation.tile.store)
    implementation(libs.tomtom.ui)
    implementation(libs.tomtom.route.planner.online)
}