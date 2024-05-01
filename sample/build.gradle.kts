plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("jp.co.japancv.plugin-sdk-config") version "1.2.1"
}

android {
    namespace = "mobile.japancv.jcvfacesdk.sample"
    compileSdk = 34

    defaultConfig {
        applicationId = "mobile.japancv.jcvfacesdk.sample"
        minSdk = 25
        targetSdk = 34
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

//repositories {
//    val repoRegistryUrl = getProperty("githubPackageUrl") ?: System.getProperty("githubPackageUrl")
//    val repoUsername = getProperty("githubUsername") ?: System.getProperty("githubUsername")
//    val repoPassword = getProperty("githubPrivateToken") ?: System.getProperty("githubPrivateToken")
//
//    maven {
//        url = uri(repoRegistryUrl)
//        name = "Github"
//        credentials {
//            username = repoUsername
//            password = repoPassword
//        }
//        authentication {
//            create("basic", BasicAuthentication::class)
//        }
//    }
//}

dependencies {
    implementation("jp.co.japancv:sdk-face-detection:3.0.0-rc.2")
    implementation("jp.co.japancv:sdk-face-attributes:3.0.0-rc.2")
    implementation("jp.co.japancv:sdk-face-liveness:3.0.0-rc.2")
    implementation("jp.co.japancv:sdk-face-pose:3.0.0-rc.2")
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}

jcvSdk {
    disableServerCheck = false
    verifyServerEndpoint = "https://license-dev.japancv.org/api/v1/verify"
}