package ua.com.poseal.helloworld.ui

import kotlinx.collections.immutable.persistentListOf
import kotlinx.parcelize.Parcelize
import ua.com.poseal.helloworld.ui.screens.item.ItemScreenArgs
import ua.com.poseal.helloworld.ui.screens.items.ItemsScreenProducer
import ua.com.poseal.helloworld.ui.screens.ProfileScreenProducer
import ua.com.poseal.helloworld.ui.screens.SettingsScreenProducer
import ua.com.poseal.helloworld.ui.screens.item.itemScreenProducer
import ua.com.poseal.navigation.Route
import ua.com.poseal.navigation.Screen

sealed class AppRoute(
    override val screenProducer: () -> Screen
) : Route {

    @Parcelize
    data class Item(
        val args: ItemScreenArgs
    ) : AppRoute(itemScreenProducer(args))

    sealed class Tab(
        screenProducer: () -> AppScreen,
    ) : AppRoute(screenProducer) {
        @Parcelize
        data object Items : Tab(ItemsScreenProducer)
        @Parcelize
        data object Settings : Tab(SettingsScreenProducer)
        @Parcelize
        data object Profile : Tab(ProfileScreenProducer)
    }
}

val RootTabs = persistentListOf(AppRoute.Tab.Items, AppRoute.Tab.Settings, AppRoute.Tab.Profile)
