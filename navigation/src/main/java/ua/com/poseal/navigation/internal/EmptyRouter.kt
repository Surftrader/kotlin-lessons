package ua.com.poseal.navigation.internal

import ua.com.poseal.navigation.Route
import ua.com.poseal.navigation.Router

internal object EmptyRouter : Router {
    override fun launch(route: Route) = Unit
    override fun pop(response: Any?) = Unit
    override fun restart(route: Route) = Unit
}
