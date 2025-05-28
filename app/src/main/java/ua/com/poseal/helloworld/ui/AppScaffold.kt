package ua.com.poseal.helloworld.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ua.com.poseal.helloworld.MainViewModel
import ua.com.poseal.helloworld.di.injectViewModel
import ua.com.poseal.helloworld.ui.scaffold.AppFloatingActionButton
import ua.com.poseal.helloworld.ui.scaffold.AppNavigationBar
import ua.com.poseal.helloworld.ui.scaffold.AppToolbar
import ua.com.poseal.helloworld.ui.theme.AppTheme
import ua.com.poseal.navigation.rememberNavigation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold() {
    val viewModel = injectViewModel<MainViewModel>()
    val navigation = rememberNavigation(RootTabs, deepLinkHandler = AppDeepLinkHandler)
    val (router, navigationState) = navigation
    val environment = navigationState.currentScreen.environment as AppScreenEnvironment

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AppToolbar(
                titleRes = environment.titleRes,
                isRoot = navigationState.isRoot,
                onPopAction = router::pop,
                onClearAction = viewModel::clear
            )
        },
        floatingActionButton = {
            AppFloatingActionButton(
                floatingAction = environment.floatingAction,
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        bottomBar = {
            AppNavigationBar(
                currentIndex = navigationState.currentStackIndex,
                onIndexSelected = router::switchStack,
            )
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
