import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlinxSerialization)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }
    
    sourceSets {
        
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            // Ktor Client (Android engine)
            implementation(libs.ktor.client.android)
            // Coil (Android network engine)
            implementation(libs.coil.network.okhttp)
        }
        iosMain.dependencies {
            // Ktor Client (iOS engine)
            implementation(libs.ktor.client.darwin)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.materialIconsExtended)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtimeCompose)

            // Kotlin Coroutines
            implementation(libs.kotlinx.coroutines.core)

            // Ktor Client (HTTP Client)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation) // Para JSON
            implementation(libs.ktor.serialization.kotlinx.json) // Serialización con Ktor

            // kotlinx.serialization (JSON)
            implementation(libs.kotlinx.serialization.json)

            // ViewModel (JetBrains official support)
            implementation(libs.androidx.lifecycle.viewmodel.compose) // Para integración con Compose

            // Inyección de Dependencias (Koin)
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)

            // Carga de Imágenes (Coil)
            implementation(libs.coil.compose)
            implementation(libs.coil.network.ktor3)
            //implementation(libs.coil.singleton)

            // Navegación (Voyager)
            //implementation(libs.voyager.navigator)
            //implementation(libs.voyager.screenmodel) // Para integrar ViewModels con Voyager

            implementation(libs.androidx.navigation.compose)

            // Logging (opcional, para depuración)
            // implementation("co.touchlab:kermit:2.0.2") // Una buena librería de logging multiplatform
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "org.cmedranv.cmpbooks"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "org.cmedranv.cmpbooks"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}

