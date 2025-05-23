package ua.com.poseal.navigation.internal

import kotlinx.coroutines.flow.Flow
import ua.com.poseal.navigation.ScreenResponseReceiver

internal sealed class NavigationEvent {
    data class Removed(val routeRecord: RouteRecord) : NavigationEvent()
}

internal interface InternalNavigationState {
    val currentUuid: String
    val screenResponseReceiver: ScreenResponseReceiver
    fun listen() : Flow<NavigationEvent>
}
