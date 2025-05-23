package ua.com.poseal.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import ua.com.poseal.navigation.internal.InternalNavigationState
import ua.com.poseal.navigation.internal.RouteRecord
import ua.com.poseal.navigation.internal.ScreenStack

@Stable
data class Navigation internal constructor(
    val router: Router,
    val navigationState: NavigationState,
    internal val internalNavigationState: InternalNavigationState,
)

@Composable
fun rememberNavigation(initialRoure: Route) : Navigation {
        val screenStack = rememberSaveable {
            ScreenStack(mutableStateListOf(RouteRecord(initialRoure)))
        }

    return remember(initialRoure) {
        Navigation(
            router = screenStack,
            navigationState = screenStack,
            internalNavigationState = screenStack
        )
    }
}
