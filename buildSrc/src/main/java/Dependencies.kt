object Android {
    const val compile_sdk_version = 28
    const val min_sdk_version = 21
    const val target_sdk_version = 28
    const val version_code = 1
    const val version_name = "0.1"
}

object Modules {
    const val core_networks = ":core-network"
    const val core_prefs = ":core-prefs"
    const val core_utils = ":core-utils"
}

object Versions {
    const val appcompat = "1.1.0"
    const val constraint_layout = "1.1.3"
    const val coil = "0.6.1"
    const val coroutines = "1.3.0"
    const val espresso_core = "3.2.0"
    const val gradle = "3.6.0-alpha12"
    const val gson = "2.8.5"
    const val junit = "4.12"
    const val koin = "2.0.1"
    const val kotlin = "1.3.50"
    const val ktx = "1.0.2"
    const val lifecycle = "2.2.0-alpha03"
    const val material = "1.0.0"
    const val navigation = "1.0.0"
    const val recycler_view = "1.0.0"
    const val retrofit = "2.6.1"
    const val okhttp = "3.12.1"

    const val rx_kotlin = "2.2.0"
    const val rx_android = "2.1.0"
    const val mockito_kotlin = "1.5.0"
    const val joda = "2.9.9.1"
    const val room = "2.1.0"
    const val dagger = "2.11"

    const val test_runner = "1.0.1"
    const val junit_ext = "1.1.1"
    const val design = "1.0.0"
    const val mp_android_chart = "v3.0.3"
    const val cicerone = "5.0.0"
}

object Libs {
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val coil = "io.coil-kt:coil:${Versions.coil}"
    const val constraint_layout = "androidx.constraintlayout:constraintlayout:${Versions.constraint_layout}"
    const val coroutines_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    const val coroutines_core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val gson = "com.google.code.gson:gson:$${Versions.gson}"
    const val koin_android = "org.koin:koin-android:${Versions.koin}"
    const val koin_core = "org.koin:koin-core:${Versions.koin}"
    const val koin_viewmodel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
    const val kotlin_std = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
    const val ktx = "androidx.core:core-ktx:${Versions.ktx}"
    const val lifecycle = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val navigation = "android.arch.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigation_ui = "android.arch.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val okhttp_logging = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
    const val recycler_view = "androidx.recyclerview:recyclerview:${Versions.recycler_view}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofit_gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"

    const val rx_android = "io.reactivex.rxjava2:rxandroid:${Versions.rx_android}"
    const val rx_kotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rx_kotlin}"
    const val joda = "net.danlew:android.joda:${Versions.joda}"
    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val dagger_compiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    const val room = "androidx.room:room-runtime:${Versions.room}"
    const val room_rx = "androidx.room:room-rxjava2:${Versions.room}"
    const val room_compiler = "androidx.room:room-compiler:${Versions.room}"
    const val cardview = "androidx.cardview:cardview:${Versions.design}"
    const val mp_chart = "com.github.PhilJay:MPAndroidChart:${Versions.mp_android_chart}"
    const val cicerone = "ru.terrakok.cicerone:cicerone:${Versions.cicerone}"
}

object TestLibs {
    const val espresso_core = "androidx.test.espresso:espresso-core:${Versions.espresso_core}"
    const val junit = "junit:junit:${Versions.junit}"
    const val mockito_kotlin = "com.nhaarman:mockito-kotlin:${Versions.mockito_kotlin}"
}
