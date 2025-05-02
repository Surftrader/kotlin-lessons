package ua.com.poseal.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import ua.com.poseal.navigation.internal.ScreenStack

@Stable
data class Navigation(
    val router: Router,
    val navigationState: NavigationState
)

@Composable
fun rememberNavigation(initialRoure: Route) : Navigation {
    return remember(initialRoure) {
        val screenStack = ScreenStack(mutableStateListOf(initialRoure))
        Navigation(
            router = screenStack,
            navigationState = screenStack
        )
    }
}
