
object Deps {

    object Androidx {
        const val core= "androidx.core:core-ktx:" + Versions.core
        const val material3 = "androidx.compose.material3:material3:" + Versions.material3
        const val activityCompose = "androidx.activity:activity-compose:" + Versions.activityCompose

        const val hiltNavigation = "androidx.hilt:hilt-navigation-compose:" + Versions.hiltNavigation

        const val liveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:" + Versions.lifeCycle
        const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:" + Versions.lifeCycle
        const val runtimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:" + Versions.lifeCycle
        const val runtimeCompose = "androidx.lifecycle:lifecycle-runtime-compose:" + Versions.lifeCycle
        const val viewmodelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:" + Versions.lifeCycle

        const val material = "androidx.compose.material:material:" + Versions.compose
        const val foundation = "androidx.compose.foundation:foundation:" + Versions.compose
        const val ui = "androidx.compose.ui:ui:" + Versions.compose
        const val uiTooling = "androidx.compose.ui:ui-tooling-preview:" + Versions.compose
        const val iconPack = "androidx.compose.material:material-icons-extended:" + Versions.compose
        const val animation = "androidx.compose.animation:animation:" + Versions.compose

        const val navigationCompose = "androidx.navigation:navigation-compose:" + Versions.navigationCompose
        const val splashScreen = "androidx.core:core-splashscreen:" + Versions.splashScreen
    }

    object Google {
        const val hilt = "com.google.dagger:hilt-android:" + Versions.hilt
        const val hiltCompiler = "com.google.dagger:hilt-compiler:" + Versions.hilt

        const val systemUiController = "com.google.accompanist:accompanist-systemuicontroller:" + Versions.accompanist
        const val navigationAnimation = "com.google.accompanist:accompanist-navigation-animation:" + Versions.accompanist
        const val navigationMaterial = "com.google.accompanist:accompanist-navigation-material:" + Versions.accompanist

        const val gmsAds = "com.google.android.gms:play-services-ads:" + Versions.gmsAds
    }

    object Kotlin {
        const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:" + Versions.coroutines
        const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:" + Versions.coroutines
    }
}