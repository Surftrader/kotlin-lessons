package ua.com.poseal.helloworld.ui.scaffold

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ua.com.poseal.helloworld.ui.RootTabs
import ua.com.poseal.navigation.Route


@Composable
fun AppNavigationBar(
    currentRoute: Route,
    onRouteSelected: (Route) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier,
    ) {
        RootTabs.forEach { tab ->
            NavigationBarItem(
                selected = currentRoute == tab,
                label = { Text(stringResource(tab.titleRes)) },
                onClick = { onRouteSelected(tab) },
                icon = {
                    Icon(
                        imageVector = tab.icon,
                        contentDescription = stringResource(tab.titleRes)
                    )
                }
            )
        }
    }
}
