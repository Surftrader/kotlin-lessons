package ua.com.poseal.navigation.links

import androidx.compose.runtime.toMutableStateList
import ua.com.poseal.navigation.Route

data class MultiStackState(

    val stacks: List<StackState>,
    val activeStackIndex: Int,

) {

    fun withNewRoute(stackIndex: Int, route: Route) : MultiStackState {
        val modifiedStacks = stacks.toMutableStateList()
        modifiedStacks[stackIndex] = modifiedStacks[stackIndex].withNewRoute(route)
        return copy(
            activeStackIndex = stackIndex,
            stacks = modifiedStacks,
        )
    }

}
