package ua.com.poseal.helloworld.ui.scaffold

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ua.com.poseal.helloworld.R
import ua.com.poseal.helloworld.ui.FloatingAction

@Composable
fun AppFloatingActionButton(
    floatingAction: FloatingAction?,
    modifier: Modifier = Modifier,
) {
    if (floatingAction != null) {
        FloatingActionButton(
            modifier = modifier,
            onClick = floatingAction.onClick
        ) {
            Icon(
                imageVector = floatingAction.icon,
                contentDescription = stringResource(R.string.add_item)
            )
        }
    }
}

@Preview
@Composable
fun AppFloatingActionButtonPreview() {
    AppFloatingActionButton(
        floatingAction = null
    )
}