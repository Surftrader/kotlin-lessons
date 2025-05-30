package ua.com.poseal.helloworld.ui

import android.net.Uri
import ua.com.poseal.helloworld.ui.screens.item.ItemScreenArgs
import ua.com.poseal.navigation.links.DeepLinkHandler
import ua.com.poseal.navigation.links.MultiStackState

object AppDeepLinkHandler : DeepLinkHandler {

    override fun handleDeepLink(uri: Uri, inputState: MultiStackState): MultiStackState {
        var outputState = inputState
        if (uri.scheme == "nav") {
            if (uri.host == "settings") {
                outputState = inputState.copy(activeStackIndex = 1)
            } else if (uri.host == "items") {
                val itemIndex = uri.pathSegments?.firstOrNull()?.toIntOrNull()
                if (itemIndex != null) {
                    val editItemRoute = AppRoute.Item(ItemScreenArgs.Edit(itemIndex))
                    outputState = inputState.withNewRoute(stackIndex = 0, editItemRoute)
                }
            }
        }
        return outputState
    }
}
