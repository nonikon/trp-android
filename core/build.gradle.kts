import com.android.build.gradle.internal.tasks.factory.dependsOn

plugins {
    id("com.android.library")
    id("com.google.devtools.ksp")
    kotlin("android")
    id("kotlin-parcelize")
}

setupCore()

android {
    namespace = "com.github.shadowsocks.core"

    defaultConfig {
        consumerProguardFiles("proguard-rules.pro")

//        externalNativeBuild.ndkBuild {
//            abiFilters("armeabi-v7a", "arm64-v8a", "x86", "x86_64")
//            arguments("-j${Runtime.getRuntime().availableProcessors()}")
//        }

        ksp {
            arg("room.incremental", "true")
            arg("room.schemaLocation", "$projectDir/schemas")
        }
    }

//    externalNativeBuild.ndkBuild.path("src/main/jni/Android.mk")

    sourceSets.getByName("androidTest") {
        assets.setSrcDirs(assets.srcDirs + files("$projectDir/schemas"))
    }

    buildFeatures.aidl = true
}

dependencies {
    val coroutinesVersion = "1.8.1"
    val roomVersion = "2.6.1"
    val workVersion = "2.9.1"

    api(project(":plugin"))
    api("androidx.core:core-ktx:1.13.1")
    api("com.google.android.material:material:1.12.0")

    api("androidx.lifecycle:lifecycle-livedata-core-ktx:$lifecycleVersion")
    api("androidx.preference:preference:1.2.1")
    api("androidx.room:room-runtime:$roomVersion")
    api("androidx.work:work-multiprocess:$workVersion")
    api("androidx.work:work-runtime-ktx:$workVersion")
    api("com.google.android.gms:play-services-oss-licenses:17.1.0")
    api("com.google.code.gson:gson:2.11.0")
    api("com.google.firebase:firebase-analytics:22.1.0")
    api("com.google.firebase:firebase-crashlytics:19.0.3")
    api("com.jakewharton.timber:timber:5.0.1")
    api("dnsjava:dnsjava:3.6.1")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:$coroutinesVersion")
    ksp("androidx.room:room-compiler:$roomVersion")
    androidTestImplementation("androidx.room:room-testing:$roomVersion")
    androidTestImplementation("androidx.test.ext:junit-ktx:1.2.1")
}
