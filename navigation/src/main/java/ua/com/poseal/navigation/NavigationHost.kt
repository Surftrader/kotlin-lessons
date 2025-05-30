package ua.com.poseal.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.filterIsInstance
import ua.com.poseal.navigation.internal.EmptyRouter
import ua.com.poseal.navigation.internal.NavigationEvent
import ua.com.poseal.navigation.viewmodel.ScreenViewModelStoreOwner
import ua.com.poseal.navigation.viewmodel.ScreenViewModelStoreProvider

val LocalRouter = staticCompositionLocalOf<Router> { EmptyRouter }

@Composable
fun NavigationHost(
    navigation: Navigation,
    modifier: Modifier = Modifier,
) {

    val (
        router,
        navigationState,
        internalState
    ) = navigation

    BackHandler(enabled = !navigationState.isRoot) {
        router.pop()
    }
    val viewModelStoreProvider =
        viewModel<ScreenViewModelStoreProvider>()
    val viewModelStoreOwner = remember(internalState.currentUuid) {
        ScreenViewModelStoreOwner(viewModelStoreProvider, internalState.currentUuid)
    }

    val saveableStateHolder = rememberSaveableStateHolder()
    saveableStateHolder.SaveableStateProvider(key = internalState.currentUuid) {
        Box(modifier = modifier) {
            CompositionLocalProvider(
                LocalRouter provides router,
                LocalScreenResponseReceiver provides internalState.screenResponseReceiver,
                LocalViewModelStoreOwner provides viewModelStoreOwner,
            ) {
                navigationState.currentScreen.Content()
            }
        }
    }

    LaunchedEffect(navigation) {
        navigation.internalNavigationState.listen()
            .filterIsInstance<NavigationEvent.Removed>()
            .collect { event ->
                saveableStateHolder.removeState(event.routeRecord.uuid)
                viewModelStoreProvider.removeStore(event.routeRecord.uuid)
            }
    }
}
