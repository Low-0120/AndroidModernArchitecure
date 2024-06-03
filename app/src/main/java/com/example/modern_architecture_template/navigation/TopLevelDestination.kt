package com.example.modern_architecture_template.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.ui.graphics.vector.ImageVector
import com.google.samples.apps.modernarchitercture.R
import designsystem.icon.BaseIcons

enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconText: Int,
    val titleTextId:Int,
) {
    MAIN(
        selectedIcon = BaseIcons.Bookmarks,
        unselectedIcon = BaseIcons.Bookmarks,
        iconText = R.string.app_name,
        titleTextId = R.string.app_name
    ),
    CENTRAL(
        selectedIcon = BaseIcons.Add,
        unselectedIcon = BaseIcons.Add,
        iconText = R.string.app_central,
        titleTextId = R.string.app_central
    ),
    MY_PAGE(
        selectedIcon = BaseIcons.Settings,
        unselectedIcon = BaseIcons.Settings,
        iconText = R.string.app_my_page,
        titleTextId = R.string.app_my_page,
    )
}