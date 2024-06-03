package com.example.modern_architecture_template.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.ui.graphics.vector.ImageVector
import com.google.samples.apps.modernarchitercture.R
import designsystem.icon.BaseIcon

enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconText: Int,
    val titleTextId:Int,
) {
    MAIN(
        selectedIcon = BaseIcon.Main,
        unselectedIcon = BaseIcon.Main,
        iconText = R.string.app_name,
        titleTextId = R.string.app_name
    ),
    CENTRAL(
        selectedIcon = BaseIcon.Central,
        unselectedIcon = BaseIcon.Central,
        iconText = R.string.app_central,
        titleTextId = R.string.app_central
    ),
    MY_PAGE(
        selectedIcon = BaseIcon.Central,
        unselectedIcon = BaseIcon.Central,
        iconText = R.string.app_central,
        titleTextId = R.string.app_central
    )
}