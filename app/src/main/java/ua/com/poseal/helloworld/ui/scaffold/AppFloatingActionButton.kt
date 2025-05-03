package ua.com.poseal.helloworld.ui.scaffold

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ua.com.poseal.helloworld.R
import ua.com.poseal.helloworld.ui.AppRoute
import ua.com.poseal.navigation.Route

@Composable
fun AppFloatingActionButton(
    currentRouter: Route,
    onLaunchAction: (AppRoute) -> Unit,
    modifier: Modifier = Modifier,
) {
    if (currentRouter == AppRoute.Tab.Items) {
        FloatingActionButton(
            modifier = modifier,
            onClick = { onLaunchAction(AppRoute.AddItem) }
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = stringResource(R.string.add_item)
            )
        }
    }
}
