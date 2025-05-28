package ua.com.poseal.navigation

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.platform.LocalContext
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import ua.com.poseal.navigation.internal.InternalNavigationState
import ua.com.poseal.navigation.internal.ScreenMultiStack
import ua.com.poseal.navigation.internal.ScreenStack
import ua.com.poseal.navigation.links.DeepLinkHandler
import ua.com.poseal.navigation.links.MultiStackState
import ua.com.poseal.navigation.links.StackState

@Stable
data class Navigation internal constructor(
    val router: Router,
    val navigationState: NavigationState,
    internal val internalNavigationState: InternalNavigationState,
)

@SuppressLint("ContextCastToActivity")
@Composable
fun rememberNavigation(
    rootRoutes: ImmutableList<Route>,
    initialIndex: Int = 0,
    deepLinkHandler: DeepLinkHandler = DeepLinkHandler.DEFAULT,
) : Navigation {
    val activity = LocalContext.current as? Activity
    val screenStack = rememberSaveable(rootRoutes) {

        val inputState = MultiStackState(
            activeStackIndex = initialIndex,
            stacks = rootRoutes.map { rootRoutes -> StackState(listOf(rootRoutes)) }
        )

        val outputState = activity?.intent?.data?.let { deepLinkUri ->
            deepLinkHandler.handleDeepLink(deepLinkUri, inputState)
        } ?: inputState

        ScreenMultiStack(
            initialIndex = outputState.activeStackIndex,
            stacks = outputState.stacks.map { stackState ->
                ScreenStack(stackState.routes)
            }.toMutableStateList()
        )
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
