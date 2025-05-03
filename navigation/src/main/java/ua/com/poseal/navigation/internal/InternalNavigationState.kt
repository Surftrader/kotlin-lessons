package ua.com.poseal.navigation.internal

import kotlinx.coroutines.flow.Flow
import ua.com.poseal.navigation.Route

sealed class NavigationEvent {
    data class Removed(val route: Route) : NavigationEvent()
}

interface InternalNavigationState {
    fun listen() : Flow<NavigationEvent>
}
