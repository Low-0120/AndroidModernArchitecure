package com.example.modern_architecture_template.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.central.navigation.CENTRAL_ROUTE
import com.example.main.navigation.MAIN_ROUTE
import com.example.modern_architecture_template.ui.compose.BaseAppState
import com.example.mypage.navigation.MY_PAGE_ROUTE

@Composable
fun BaseNavHost(
    appState: BaseAppState,
    onShowSnackbar: suspend (String, String?) -> Boolean,
    modifier: Modifier = Modifier,
    startDestination: String = MAIN_ROUTE,
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        composable(MAIN_ROUTE) {

        }
        composable(CENTRAL_ROUTE) {

        }
        composable(MY_PAGE_ROUTE) {

        }
    }
}

