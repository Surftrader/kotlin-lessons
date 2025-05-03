package ua.com.poseal.helloworld.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ua.com.poseal.helloworld.ItemsRepository
import ua.com.poseal.helloworld.R
import ua.com.poseal.helloworld.ui.scaffold.AppFloatingActionButton
import ua.com.poseal.helloworld.ui.scaffold.AppNavigationBar
import ua.com.poseal.helloworld.ui.scaffold.AppToolbar
import ua.com.poseal.helloworld.ui.theme.AppTheme
import ua.com.poseal.navigation.rememberNavigation

val RootTabs = listOf(AppRoute.Tab.Items, AppRoute.Tab.Settings, AppRoute.Tab.Profile)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold() {
    val itemsRepository: ItemsRepository = ItemsRepository.get()
    val navigation = rememberNavigation(initialRoure = AppRoute.Tab.Items)
    val (router, navigationState) = navigation

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AppToolbar(
                titleRes = (navigationState.currentRoute as? AppRoute)?.titleRes ?: R.string.app_name,
                isRoot = navigationState.isRoot,
                onPopAction = router::pop,
                onClearAction = itemsRepository::clear
            )
        },
        floatingActionButton = {
            AppFloatingActionButton(
                currentRouter = navigationState.currentRoute,
                onLaunchAction = router::launch
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        bottomBar = {
            if(navigationState.isRoot) {
                AppNavigationBar(
                    currentRoute = navigationState.currentRoute,
                    onRouteSelected = router::restart,
                )
            }
        }
    ) { paddingValues ->
        AppNavigationHost(
            modifier = Modifier.padding(paddingValues),
            navigation = navigation
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun AppScreenPreview() {
    AppTheme(darkTheme = true) {
        AppScaffold()

    }
}
