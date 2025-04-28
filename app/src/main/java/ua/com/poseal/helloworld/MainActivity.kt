package ua.com.poseal.helloworld

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScreen() {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBar(
            title = { Text(text = "Random Title") },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        )

        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1.0f)
        ) {
            // TODO: content here
        }

        NavigationBar {

            NavigationBarItem(
                selected = true,
                onClick = { /*TODO*/ },
                icon = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.List,
                        contentDescription = null,
                    )
                },
                label = {
                    Text(text = stringResource(R.string.items))
                },
                enabled = true,
                alwaysShowLabel = true, // when selected = false - label(text) will be invisible
                colors = NavigationBarItemDefaults.colors(
                    unselectedIconColor = Color.Blue, // when selected = false
                    selectedIconColor = Color.Red, // when selected = true
                    disabledIconColor = Color.LightGray, // when enabled = false
                    // The same for label:
                    unselectedTextColor = Color.Blue,
                    selectedTextColor = Color.Red,
                    disabledTextColor = Color.LightGray,

                    indicatorColor = Color.Yellow // when selected = true
                )
            )

            NavigationBarItem(
                selected = false,
                onClick = { /*TODO*/ },
                icon = {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = null,
                    )
                },
                label = {
                    Text(text = stringResource(R.string.settings))
                },
                enabled = true,
                alwaysShowLabel = false,
                colors = NavigationBarItemDefaults.colors(
                    unselectedIconColor = Color.Blue,
                    selectedIconColor = Color.Red,
                    disabledIconColor = Color.LightGray,
                    unselectedTextColor = Color.Blue,
                    selectedTextColor = Color.Red,
                    disabledTextColor = Color.LightGray,
                    indicatorColor = Color.Yellow
                )
            )

            NavigationBarItem(
                selected = false,
                onClick = { /*TODO*/ },
                icon = {
                    Icon(
                        imageVector = Icons.Default.AccountBox,
                        contentDescription = null,
                    )
                },
                label = {
                    Text(text = stringResource(R.string.profile))
                },
                enabled = false,
                alwaysShowLabel = true,
                colors = NavigationBarItemDefaults.colors(
                    unselectedIconColor = Color.Blue,
                    selectedIconColor = Color.Red,
                    disabledIconColor = Color.LightGray,
                    unselectedTextColor = Color.Blue,
                    selectedTextColor = Color.Red,
                    disabledTextColor = Color.LightGray,
                    indicatorColor = Color.Yellow
                )
            )
        }

    }
}

@Preview(showSystemUi = true)
@Composable
fun AppScreenPreview() {
    AppScreen()
}
