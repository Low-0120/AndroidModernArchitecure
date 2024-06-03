package com.example.central.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions


const val LINKED_CENTRAL_RESOURCE_ID = "linkedCentralResourceId"
const val CENTRAL_ROUTE = "Central_route/{$LINKED_CENTRAL_RESOURCE_ID}"
private const val DEEP_LINK_URI_PATTERN = ""

fun NavController.navigateToCentral(navOptions: NavOptions) = navigate(CENTRAL_ROUTE,navOptions)
