package com.example.mypage.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions

const val LINKED_MY_PAGE_RESOURCE_ID = "linkedMyPageResourceId"
const val MY_PAGE_ROUTE = "my_page_route/{$LINKED_MY_PAGE_RESOURCE_ID}"
private const val DEEP_LINK_URI_PATTERN = ""

fun NavController.navigateToMyPage(navOptions: NavOptions) = navigate(MY_PAGE_ROUTE,navOptions)
