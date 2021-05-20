plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-android")
}

dependencies {
    implementation(project(":shared"))
    implementation("com.google.android.material:material:1.3.0")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${rootProject.extra["kotlin_version"]}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")

    //added
    implementation("com.google.android.gms:play-services-maps:17.0.1")
    implementation("com.google.android.gms:play-services-maps:17.0.1")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation("com.google.firebase:firebase-auth:21.0.1")
    //implementation("com.google.firebase:firebase-database:20.0.0'")



   // implementation("androidx.recyclerview:recyclerview:1.2.0'")
   // implementation("androidx.legacy:legacy-support-v4:1.0.0'")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
    implementation("androidx.cardview:cardview:1.0.0")

    //Retrofi and OkHttp
    implementation("com.squareup.okhttp3:okhttp:3.12.0")
    //implementation("com.google.code.gson:gson:2.8.5")
    implementation("com.squareup.retrofit2:retrofit:2.5.0")
    implementation("com.squareup.okhttp3:logging-interceptor:3.9.1")
    implementation("com.squareup.retrofit2:converter-gson:2.5.0")
    implementation("com.squareup.retrofit2:converter-scalars:2.3.0")

    //Gson
    implementation("com.google.code.gson:gson:2.8.6")

    //RXJava
    implementation("io.reactivex.rxjava2:rxandroid:2.1.0")
    implementation("io.reactivex.rxjava2:rxjava:2.2.2")
    implementation("com.squareup.retrofit2:adapter-rxjava2:2.5.0")

    //Dagger
    implementation("com.google.dagger:dagger:2.28.3")
    implementation("com.google.android.gms:play-services-maps:17.0.1")
    // kapt 'com.google.dagger:dagger-compiler:2.17'

    //Glide
    implementation("com.github.bumptech.glide:glide:4.11.0")
    //kapt 'com.github.bumptech.glide:compiler:4.8.0'


    implementation("androidx.cardview:cardview:1.0.0")

    implementation("com.intuit.ssp:ssp-android:1.0.6")
    implementation("com.intuit.sdp:sdp-android:1.0.6")

    implementation("com.google.android.material:material:1.3.0")

    implementation("com.google.android.libraries.places:places:2.4.0")

    implementation("com.google.android.gms:play-services-places:17.0.0")

    implementation("de.hdodenhof:circleimageview:3.1.0")

    implementation("com.google.android.gms:play-services-location:18.0.0")

    implementation("com.chaos.view:pinview:1.4.3")
    implementation("com.hbb20:ccp:2.4.0")

    implementation("androidx.multidex:multidex:2.0.1")

    implementation("com.xwray:groupie:2.1.0")

    implementation("androidx.browser:browser:1.3.0")
    implementation("androidx.recyclerview:recyclerview:1.2.0")
}

android {
    compileSdkVersion(30)
    defaultConfig {
        applicationId = "com.intellect.ello.android"
        minSdkVersion(16)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"
        multiDexEnabled = true
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    packagingOptions {
        exclude("META-INF/DEPENDENCIES")
        exclude("META-INF/LICENSE")
        exclude("META-INF/LICENSE.txt")
        exclude("META-INF/license.txt") 
        exclude("META-INF/NOTICE")
        exclude("META-INF/NOTICE.txt")
        exclude("META-INF/notice.txt")
        exclude("META-INF/ASL2.0")
        exclude("META-INF/*.kotlin_module")
    }
}