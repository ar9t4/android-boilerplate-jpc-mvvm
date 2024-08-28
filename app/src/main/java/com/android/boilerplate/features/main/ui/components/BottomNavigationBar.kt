package com.android.boilerplate.features.main.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.android.boilerplate.features.main.domain.model.NavigationItem

/**
 * Created by Abdul Rahman on 29/05/2024
 */
@Composable
internal fun BottomNavigationBar(navController: NavHostController) {
    val navigationItems = NavigationItem.getItems()
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
        tonalElevation = 0.dp
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        navigationItems.forEach { navItem ->
            val isSelected =
                currentDestination?.hierarchy?.any { it.route == navItem.route } == true
            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    navController.navigate(navItem.route) {
                        // pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // avoid multiple copies of the same destination when
                        // re-selecting the same item
                        launchSingleTop = true
                        // restore state when re-selecting a previously selected item
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        painter =
                        painterResource(id = if (isSelected) navItem.filled else navItem.unfilled),
                        contentDescription = null
                    )
                },
                label = { Text(stringResource(navItem.resourceId)) },
                colors = NavigationBarItemColors(
                    selectedIconColor = MaterialTheme.colorScheme.onSurface,
                    selectedTextColor = MaterialTheme.colorScheme.onSurface,
                    selectedIndicatorColor = MaterialTheme.colorScheme.surface,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurface,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurface,
                    disabledIconColor = MaterialTheme.colorScheme.onSurface,
                    disabledTextColor = MaterialTheme.colorScheme.onSurface
                )
            )
        }
    }
}