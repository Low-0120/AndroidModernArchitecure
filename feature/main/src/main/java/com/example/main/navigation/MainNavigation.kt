package com.example.main.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions

const val LINKED_MAIN_RESOURCE_ID = "linkedMainResourceId"
const val MAIN_ROUTE = "main_route/{$LINKED_MAIN_RESOURCE_ID}"
private const val DEEP_LINK_URI_PATTERN = ""

fun NavController.navigateToMain(navOptions: NavOptions) = navigate(MAIN_ROUTE,navOptions)
