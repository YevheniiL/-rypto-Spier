import com.google.firebase.appdistribution.gradle.AppDistributionExtension
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile
import java.io.FileInputStream
import java.util.Properties

val keystorePropertiesFile = rootProject.file("keystore.properties")
val keystoreProperties =
    Properties().apply {
        load(FileInputStream(keystorePropertiesFile))
    }

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ktlint)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.firebase)
    alias(libs.plugins.firebase.appdistribution)
}

android {
    namespace = "com.home.petprojectv2"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.home.petprojectv2"
        minSdk = 35
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        buildConfig = true
    }

    signingConfigs {
        create("release") {
            // Read properties from the loaded file
            storeFile = file(keystoreProperties.getProperty("storeFile"))
            storePassword = keystoreProperties.getProperty("storePassword")
            keyAlias = keystoreProperties.getProperty("keyAlias")
            keyPassword = keystoreProperties.getProperty("keyPassword")
        }
    }

    buildTypes {
        getByName("debug") {
            // Defines a variable accessible in your code via BuildConfig.BASE_URL
            buildConfigField("String", "USER_AUTH_URL", "\"https://identitytoolkit.googleapis.com/v1/accounts\"")

            // Allows installing dev/debug builds alongside others
            applicationIdSuffix = ".dev"
            versionNameSuffix = "-dev"
            isDebuggable = true

            firebaseAppDistribution {
                artifactType = "APK"
                appId = "1:377641702739:android:466a7af6b089ef79c3bda6"
                artifactPath = "app/debug/app-debug.apk"
                // releaseNotesFile = "/path/to/releasenotes.txt"
                groups = "qa-team"
            }
        }

        // 'release' will be your 'prod' environment
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            signingConfig = signingConfigs.getByName("release")
            buildConfigField("String", "USER_AUTH_URL", "\"https://identitytoolkit.googleapis.com/v1/accounts\"")

            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")

            firebaseAppDistribution {
                artifactType = "APK"
                appId = "1:567700455219:android:6235d4cd4a754b9c360846"
                artifactPath = "courier-assistant/build/outputs/apk/fuerth/debug/courier-assistant-fuerth-debug.apk"
                // releaseNotesFile = "/path/to/releasenotes.txt"
                groups = "qa-team"
            }
        }

        create("stage") {
            // This copies settings from 'release' (like isMinifyEnabled, signingConfig)
            initWith(getByName("release"))

            buildConfigField("String", "USER_AUTH_URL", "\"https://identitytoolkit.googleapis.com/v1/accounts\"")

            // Allows installing stage builds alongside others
            applicationIdSuffix = ".stage"
            isDebuggable = false // Staging should ideally not be debuggable
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    tasks.withType<KotlinJvmCompile>().configureEach {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
            freeCompilerArgs.add("-opt-in=kotlin.RequiresOptIn")
        }
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.splashscreen)

    // Navigation
    implementation(libs.androidx.navigation3.ui)
    implementation(libs.androidx.navigation3.runtime)
    implementation(libs.androidx.lifecycle.viewmodel.navigation3)
    implementation(libs.androidx.material3.adaptive.navigation3)
    implementation(libs.androidx.hilt.navigation.compose)

    implementation(libs.kotlinx.serialization.core)
    implementation(libs.kotlinx.serialization.json)

    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    // Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)

    // Retrofit - Networking
    implementation(libs.retrofit.retrofit)
    implementation(libs.retrofit.gson)
    implementation(libs.okhttp3.logging.interceptor)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
