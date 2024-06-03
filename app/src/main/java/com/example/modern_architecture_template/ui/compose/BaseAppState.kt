package com.example.modern_architecture_template.ui.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.example.central.navigation.CENTRAL_ROUTE
import com.example.central.navigation.navigateToCentral
import com.example.main.navigation.MAIN_ROUTE
import com.example.main.navigation.navigateToMain
import com.example.modern_architecture_template.navigation.TopLevelDestination
import com.example.mypage.navigation.MY_PAGE_ROUTE
import com.example.mypage.navigation.navigateToMyPage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.datetime.TimeZone
import util.NetworkMonitor
import util.TimeZoneMonitor

@Composable
fun rememberBaseAppState(
    networkMonitor: NetworkMonitor,
    timeZoneMonitor: TimeZoneMonitor,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
): BaseAppState {
    return remember(
        navController,
        coroutineScope,
        networkMonitor,
        timeZoneMonitor,
    ) {
        BaseAppState(
            navController = navController,
            coroutineScope = coroutineScope,
            networkMonitor = networkMonitor,
            timeZoneMonitor = timeZoneMonitor,
        )
    }
}

@Stable
class BaseAppState(
    val navController: NavHostController,
    coroutineScope: CoroutineScope,
    networkMonitor: NetworkMonitor,
    timeZoneMonitor: TimeZoneMonitor,
) {
    val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() = when (currentDestination?.route) {
            MAIN_ROUTE -> TopLevelDestination.MAIN
            CENTRAL_ROUTE -> TopLevelDestination.CENTRAL
            MY_PAGE_ROUTE -> TopLevelDestination.MY_PAGE
            else -> null

        }

    val isOffline = networkMonitor.isOnline
        .map { Boolean::not }
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false
        )

    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.entries

    val currentTimeZone = timeZoneMonitor.currentTimeZone
        .stateIn(
            coroutineScope,
            SharingStarted.WhileSubscribed(5_000),
            TimeZone.currentSystemDefault(),
        )

    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        androidx.tracing.trace("Navigation:${topLevelDestination.name}") {
            val topLevelNavOptions = navOptions {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
            when (topLevelDestination) {
                TopLevelDestination.MAIN -> navController.navigateToMain(topLevelNavOptions)
                TopLevelDestination.CENTRAL -> navController.navigateToCentral(topLevelNavOptions)
                TopLevelDestination.MY_PAGE -> navController.navigateToMyPage(topLevelNavOptions)

            }
        }
    }
}
