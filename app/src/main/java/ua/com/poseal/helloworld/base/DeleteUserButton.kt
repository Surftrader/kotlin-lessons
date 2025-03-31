package ua.com.poseal.helloworld.base

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ua.com.poseal.helloworld.R

@Composable
fun RowScope.DeleteUserButton(onClick: () -> Unit) {
    IconButton(
        onClick = onClick,
        modifier = Modifier.align(Alignment.CenterVertically)
    ) {
        Icon(
            imageVector = Icons.Rounded.Delete,
            contentDescription = stringResource(R.string.delete_user),
        )
    }
}
