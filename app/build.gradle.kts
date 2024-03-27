plugins {
	id("com.android.application")
	id("org.jetbrains.kotlin.android")
	id("com.google.dagger.hilt.android")
	id("kotlin-kapt")
}

android {
	namespace = "io.rapidz.jetpackcomposetraining_assignment0"
	compileSdk = 34

	defaultConfig {
		applicationId = "io.rapidz.jetpackcomposetraining_assignment0"
		minSdk = 24
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
			proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
	}
	composeOptions {
		kotlinCompilerExtensionVersion = "1.5.11"
	}
	packaging {
		resources {
			excludes += "/META-INF/{AL2.0,LGPL2.1}"
		}
	}
}

dependencies {

	implementation("androidx.core:core-ktx:1.12.0")
	implementation("androidx.appcompat:appcompat:1.6.1")
	implementation("com.google.android.material:material:1.11.0")
	implementation(platform("androidx.compose:compose-bom:2024.03.00"))
	implementation("androidx.compose.material3:material3")
	testImplementation("junit:junit:4.13.2")
	androidTestImplementation("androidx.test.ext:junit:1.1.5")
	androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

	// Compose
	implementation("androidx.activity:activity-compose:1.8.2")

	// Load local drawable
	implementation("com.google.accompanist:accompanist-drawablepainter:0.35.0-alpha")

	// Timber
	implementation("com.jakewharton.timber:timber:5.0.1")

	// Coroutines
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

	// Test
	androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
	androidTestImplementation("androidx.compose.ui:ui-test-junit4")
	debugImplementation("androidx.compose.ui:ui-tooling")
	debugImplementation("androidx.compose.ui:ui-test-manifest")

	// Coroutine Lifecycle Scopes
	val lifecycleVersion = "2.7.0"
	implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
	implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion")

	// Dagger Hilt
	// Not support KSP
	val daggerVersion = "2.44"
	implementation("com.google.dagger:hilt-android:$daggerVersion")
	kapt("com.google.dagger:hilt-android-compiler:$daggerVersion")
}

// Allow references to generated code
kapt {
	correctErrorTypes = true
}