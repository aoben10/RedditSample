
plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("com.github.ben-manes.versions")
}

android {
    compileSdkVersion(28)
    defaultConfig {
        applicationId = "com.theobencode.victoroben.sampleproject"
        minSdkVersion(23)
        targetSdkVersion(28)
        versionName = "1.0"
        versionCode = 1
    }

    compileOptions {
        targetCompatibility = JavaVersion.VERSION_1_8
        sourceCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(Libraries.kotlinStdLib)
    implementation(Libraries.materialComponents)
    implementation(Libraries.appcompat)
    implementation(Libraries.constraintLayout)
    implementation(Libraries.coreKtx)
    implementation(Libraries.lifecycleExtensions)
    implementation(Libraries.lifecycleViewModelExtensions)
    implementation(Libraries.coroutinesCore)
    implementation(Libraries.coroutinesAndroid)
    implementation(Libraries.logger)
    implementation(Libraries.glide)
    implementation(Libraries.navigationFragment)
    implementation(Libraries.navigationUi)
    implementation(Libraries.koinAndroid)
    implementation(Libraries.koinViewmodel)
    implementation(Libraries.roomRuntime)
    implementation(Libraries.roomKtx)
    implementation(Libraries.retrofit2)
    implementation(Libraries.moshiConverter)
    implementation(Libraries.retrofitCoroutinesAdapter)
    implementation(Libraries.okHttp)
    implementation(Libraries.moshi)
    implementation(Libraries.moshiKotlin)
    implementation(Libraries.liveDataKtx)
    implementation(Libraries.swipeRefresh)

    debugImplementation(Libraries.okHttpLoggingInterceptor)

    testImplementation(TestLibraries.junit)
    testImplementation(TestLibraries.roboelectric)
    testImplementation(TestLibraries.androidXTestCore)
    testImplementation(TestLibraries.mockitoCore)
    testImplementation(TestLibraries.koinTest)

    kapt(KaptLibraries.moshiKotlinCodegen)
    kapt(KaptLibraries.glideCompiler)

}
