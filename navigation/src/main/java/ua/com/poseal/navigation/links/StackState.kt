package ua.com.poseal.navigation.links

import ua.com.poseal.navigation.Route

data class StackState(
    val routes: List<Route>,
) {

    fun withNewRoute(route: Route) : StackState = copy(
        routes = routes + route
    )

}
