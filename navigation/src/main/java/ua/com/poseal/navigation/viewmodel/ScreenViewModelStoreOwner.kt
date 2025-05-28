package ua.com.poseal.navigation.viewmodel

import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner

class ScreenViewModelStoreOwner(
    screenViewModelStoreProvider: ScreenViewModelStoreProvider,
    uuid: String,
) : ViewModelStoreOwner {

    override val viewModelStore: ViewModelStore =
        screenViewModelStoreProvider.getStore(uuid)
}
