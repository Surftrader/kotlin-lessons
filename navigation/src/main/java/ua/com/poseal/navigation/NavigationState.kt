package ua.com.poseal.navigation

import androidx.compose.runtime.Stable

@Stable
interface NavigationState {
    val isRoot: Boolean
    val currentRoute: Route
    val currentScreen: Screen
}
