import org.gradle.api.Plugin
import org.gradle.api.Project

class Dependencies : Plugin<Project> {

    override fun apply(target: Project) {}

    companion object {

        const val core = Deps.Androidx.core
        const val material3 = Deps.Androidx.material3
        const val activityCompose = Deps.Androidx.activityCompose
        const val hiltNavigation = Deps.Androidx.hiltNavigation
        const val liveDataKtx = Deps.Androidx.liveDataKtx
        const val viewModelKtx = Deps.Androidx.viewModelKtx
        const val runtimeKtx = Deps.Androidx.runtimeKtx
        const val runtimeCompose = Deps.Androidx.runtimeCompose
        const val viewmodelCompose = Deps.Androidx.viewmodelCompose
        const val material = Deps.Androidx.material
        const val foundation = Deps.Androidx.foundation
        const val ui = Deps.Androidx.ui
        const val uiTooling = Deps.Androidx.uiTooling
        const val iconPack = Deps.Androidx.iconPack
        const val animation = Deps.Androidx.animation
        const val navigationCompose = Deps.Androidx.navigationCompose
        const val splashScreen = Deps.Androidx.splashScreen

        const val hilt = Deps.Google.hilt
        const val hiltCompiler = Deps.Google.hiltCompiler
        const val systemUiController = Deps.Google.systemUiController
        const val navigationAnimation = Deps.Google.navigationAnimation
        const val navigationMaterial = Deps.Google.navigationMaterial
        const val gmsAds = Deps.Google.gmsAds

        const val coroutinesCore = Deps.Kotlin.coroutinesCore
        const val coroutinesAndroid = Deps.Kotlin.coroutinesAndroid

        const val billingClient = "com.android.billingclient:billing:5.1.0"
        const val guava = "com.google.guava:listenablefuture:9999.0-empty-to-avoid-conflict-with-guava"
        const val guavaJre = "com.google.guava:guava:24.1-jre"

        const val coil = "io.coil-kt:coil-compose:2.3.0"
        const val dataStore = "androidx.datastore:datastore-preferences:1.0.0"

        const val retrofit2 = "com.squareup.retrofit2:retrofit:2.9.0"
        const val okHttp3 = "com.squareup.okhttp3:okhttp:5.0.0-alpha.2"
        const val gsonConverter = "com.squareup.retrofit2:converter-gson:2.9.0"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2"

        const val room = "androidx.room:room-ktx:2.5.1"
        const val roomRuntime = "androidx.room:room-runtime:2.5.1"
        const val roomCompiler = "androidx.room:room-compiler:2.5.1"

        const val lottie = "com.airbnb.android:lottie-compose:5.2.0"
    }
}