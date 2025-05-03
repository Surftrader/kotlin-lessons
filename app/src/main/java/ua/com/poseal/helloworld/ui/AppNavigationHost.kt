package ua.com.poseal.helloworld.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ua.com.poseal.helloworld.ui.screens.AddItemScreen
import ua.com.poseal.helloworld.ui.screens.ItemsScreen
import ua.com.poseal.helloworld.ui.screens.ProfileScreen
import ua.com.poseal.helloworld.ui.screens.SettingsScreen
import ua.com.poseal.navigation.Navigation
import ua.com.poseal.navigation.NavigationHost

@Composable
fun AppNavigationHost(
    modifier: Modifier = Modifier,
    navigation: Navigation,
) {
    NavigationHost(
        navigation = navigation,
        modifier = modifier
    ) { currentRoute ->
        when (currentRoute) {
            AppRoute.Tab.Items -> ItemsScreen()
            AppRoute.Tab.Settings -> SettingsScreen()
            AppRoute.Tab.Profile -> ProfileScreen()
            AppRoute.AddItem -> AddItemScreen()
        }
    }
}
