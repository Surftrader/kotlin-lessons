package ua.com.poseal.helloworld.ui.scaffold

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import ua.com.poseal.helloworld.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppToolbar(
    @StringRes titleRes: Int,
    isRoot: Boolean,
    onPopAction: () -> Unit = {},
    onClearAction: () -> Unit = {},
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = stringResource(titleRes),
                fontWeight = FontWeight.Bold,
            )
        },
        colors = TopAppBarDefaults.largeTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        navigationIcon = {
            IconButton(
                onClick = {
                    if (!isRoot) {
                        onPopAction
                    }
                }
            ) {
                Icon(
                    imageVector = if (isRoot) {
                        Icons.Default.Menu
                    } else {
                        Icons.AutoMirrored.Filled.ArrowBack
                    },
                    contentDescription = stringResource(R.string.main_menu)
                )
            }
        },
        actions = {
            var showPopupMenu by remember { mutableStateOf(false) }
            val context = LocalContext.current
            IconButton(
                onClick = { showPopupMenu = true }
            ) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = stringResource(R.string.more_actions)
                )
            }
            DropdownMenu(
                expanded = showPopupMenu,
                onDismissRequest = { showPopupMenu = false }
            ) {
                DropdownMenuItem(
                    text = { Text(stringResource(R.string.about)) },
                    onClick = {
                        Toast.makeText(
                            context,
                            R.string.scaffold_app,
                            Toast.LENGTH_SHORT
                        ).show()
                        showPopupMenu = false
                    }
                )
                DropdownMenuItem(
                    text = { Text(stringResource(R.string.clear)) },
                    onClick = {
                        onClearAction()
                        showPopupMenu = false
                    },
                )
            }
        }
    )
}

@Preview(showSystemUi = true)
@Composable
private fun AppToolbarRootPreview() {
    AppToolbar(
        titleRes = R.string.scaffold_app,
        isRoot= true,
    )
}

@Preview(showSystemUi = true)
@Composable
private fun AppToolbarNotRootPreview() {
    AppToolbar(
        titleRes = R.string.scaffold_app,
        isRoot= false,
    )
}
