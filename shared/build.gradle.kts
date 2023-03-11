plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    kotlin("plugin.serialization")
    id("com.android.library")
}

kotlin {
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
        }
    }
    
    sourceSets {
        val koinVersion = "3.2.0"
        val ktorVersion = "2.2.3"
        val coroutinesVersion = "1.6.4"
        val settingsVersion = "1.0.0"
        val lifecycleVersion = "2.5.1"
        val turbineVersion = "0.12.1"

        all {
            languageSettings.optIn("kotlin.experimental.ExperimentalObjCName")
        }
        val commonMain by getting {
            dependencies {
                implementation("io.insert-koin:koin-core:$koinVersion")
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
                implementation("com.russhwolf:multiplatform-settings:$settingsVersion")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation("io.ktor:ktor-client-mock:$ktorVersion")
                implementation("io.insert-koin:koin-test:$koinVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesVersion")
                implementation("com.russhwolf:multiplatform-settings-test:$settingsVersion")
                implementation("app.cash.turbine:turbine:$turbineVersion")
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-okhttp:$ktorVersion")
                implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
            }
        }
        val androidUnitTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                implementation("io.ktor:ktor-client-darwin:$ktorVersion")
            }
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    namespace = "com.maruchin.kmm.sharedmvi"
    compileSdk = 33
    defaultConfig {
        minSdk = 26
        targetSdk = 33
    }
}
