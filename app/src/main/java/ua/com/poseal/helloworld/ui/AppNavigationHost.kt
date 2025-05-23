package ua.com.poseal.helloworld.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
    )
}
