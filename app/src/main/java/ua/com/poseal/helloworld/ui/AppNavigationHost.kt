package ua.com.poseal.helloworld.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ua.com.poseal.navigation.Navigation
import ua.com.poseal.navigation.NavigationHost
import ua.com.poseal.navigation.rememberNavigation

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

@Preview
@Composable
fun AppNavigationHostPreview() {
    AppNavigationHost(
        navigation = rememberNavigation(RootTabs)
    )
}
