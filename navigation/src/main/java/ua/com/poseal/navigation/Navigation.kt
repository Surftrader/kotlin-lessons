package ua.com.poseal.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import ua.com.poseal.navigation.internal.InternalNavigationState
import ua.com.poseal.navigation.internal.ScreenMultiStack
import ua.com.poseal.navigation.internal.ScreenStack

@Stable
data class Navigation internal constructor(
    val router: Router,
    val navigationState: NavigationState,
    internal val internalNavigationState: InternalNavigationState,
)

@Composable
fun rememberNavigation(
    rootRoutes: ImmutableList<Route>,
    initialIndex: Int = 0,
) : Navigation {
        val screenStack = rememberSaveable(rootRoutes) {
            val stacks = SnapshotStateList<ScreenStack>()
            stacks.addAll(rootRoutes.map(::ScreenStack))
            ScreenMultiStack(stacks, initialIndex)
        }

    return remember(rootRoutes) {
        Navigation(
            router = screenStack,
            navigationState = screenStack,
            internalNavigationState = screenStack
        )
    }
}

@Composable
fun rememberNavigation(
    initialRoute: Route,
) : Navigation = rememberNavigation(persistentListOf(initialRoute))
