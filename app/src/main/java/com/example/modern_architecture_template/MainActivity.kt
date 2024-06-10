package com.example.modern_architecture_template

import LocalTimeZone
import analytics.AnalyticsHelper
import analytics.LocalAnalyticsHelper
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.metrics.performance.JankStats
import com.example.modern_architecture_template.ui.compose.BaseApp
import com.example.modern_architecture_template.ui.compose.rememberBaseAppState
import com.example.modern_architecture_template.ui.theme.ModernarchitecturetemplateTheme
import dagger.hilt.android.AndroidEntryPoint
import designsystem.theme.BaseTheme
import util.NetworkMonitor
import util.TimeZoneMonitor
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var lazyStats: dagger.Lazy<JankStats>

    @Inject
    lateinit var networkMonitor: NetworkMonitor

    @Inject
    lateinit var timeZoneMonitor: TimeZoneMonitor

    @Inject
    lateinit var analyticsHelper: AnalyticsHelper


    val viewModel: MainActivityViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        splashScreen.setKeepOnScreenCondition {
            System.currentTimeMillis() < 10000
            //TODO:: viewmodelのユーザーデータのフェッチ感知させる
        }
        enableEdgeToEdge()
        setContent {

            val appState = rememberBaseAppState(
                networkMonitor = networkMonitor,
                timeZoneMonitor = timeZoneMonitor
            )

            val currentTimeZone by appState.currentTimeZone.collectAsStateWithLifecycle()

            CompositionLocalProvider(
                LocalAnalyticsHelper provides analyticsHelper,
                LocalTimeZone provides  currentTimeZone
            ){
                BaseTheme{
                    BaseApp(appState = appState)

                }



            }

        }
    }

    override fun onResume() {
        super.onResume()
        lazyStats.get().isTrackingEnabled = true
    }

    override fun onPause() {
        super.onPause()
        lazyStats.get().isTrackingEnabled = false
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ModernarchitecturetemplateTheme {
        Greeting("Android")
    }
}