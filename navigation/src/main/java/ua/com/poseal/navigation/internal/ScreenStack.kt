package ua.com.poseal.navigation.internal

import androidx.compose.runtime.snapshots.SnapshotStateList
import ua.com.poseal.navigation.NavigationState
import ua.com.poseal.navigation.Route
import ua.com.poseal.navigation.Router

internal class ScreenStack(
    private val routes: SnapshotStateList<Route>,
) : NavigationState, Router {

    // realization methods of NavigationState
    override val isRoot: Boolean
        get() = routes.size == 1

    override val currentRoute: Route
        get() = routes.last()

    // realization methods of Router
    override fun launch(route: Route) {
        routes.add(route)
    }

    override fun pop() {
        routes.removeLastOrNull()
    }

    override fun restart(route: Route) {
        routes.apply {
            clear()
            add(route)
        }
    }
}
