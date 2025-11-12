plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("kotlin-kapt") // Plugin necessário para o Room funcionar
}

android {
    namespace  = "com.example.od2characterapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.od2characterapp"
        minSdk = 21
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

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

    buildFeatures {
        compose = true
    }

    composeOptions {
        // Mantenha 1.6.0 ou atualize conforme o Kotlin
        kotlinCompilerExtensionVersion = "1.6.0"
    }

    kotlinOptions {
        jvmTarget = "17"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    // Definindo as versões para melhor organização e atualização
    val room_version = "2.6.1"
    val lifecycle_version = "2.8.0"
    val coroutines_version = "1.8.1"
    val compose_version = "1.6.0"
    val core_ktx_version = "1.13.1"

    // Core KTX
    implementation("androidx.core:core-ktx:$core_ktx_version")

    // Activity e Compose
    implementation("androidx.compose.material3:material3:1.2.1")
    implementation("androidx.compose.material:material-icons-extended:1.6.0")

    implementation("androidx.activity:activity-compose:1.9.0") // Atualizado
    implementation("androidx.compose.ui:ui:$compose_version")
    implementation("androidx.compose.material:material:$compose_version")
    implementation("androidx.compose.ui:ui-tooling-preview:$compose_version")

    // Lifecycle / ViewModel
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version") // Atualizado
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version") // Atualizado

    // Room (Corrigindo o KAPT com a versão mais recente e estável)
    implementation("androidx.room:room-runtime:$room_version")
    kapt("androidx.room:room-compiler:$room_version") // O Room Compiler DEVE usar 'kapt'
    implementation("androidx.room:room-ktx:$room_version") // Para o uso de 'suspend' (Coroutines)

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version") // Atualizado

    // Navigation Compose (opcional)
    implementation("androidx.navigation:navigation-compose:2.7.0")

    // Tooling
    debugImplementation("androidx.compose.ui:ui-tooling:$compose_version")

    // Dependências de Teste
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}