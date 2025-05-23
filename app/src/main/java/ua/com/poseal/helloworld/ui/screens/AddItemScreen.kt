package ua.com.poseal.helloworld.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.com.poseal.helloworld.ui.AppRoute
import ua.com.poseal.helloworld.ItemsRepository
import ua.com.poseal.helloworld.R
import ua.com.poseal.helloworld.ui.AppScreen
import ua.com.poseal.helloworld.ui.AppScreenEnvironment
import ua.com.poseal.navigation.LocalRouter

val AddItemScreenProducer = { AddItemScreen() }

class AddItemScreen : AppScreen {

    override val environment = AppScreenEnvironment().apply {
        titleRes = R.string.add_item
    }

    @Composable
    override fun Content() {
        val itemsRepository = ItemsRepository.get()
        val router = LocalRouter.current
        AddItemContent(
            onSubmitNewItem = {
                itemsRepository.addItem(it)
                router.pop()
            },
            onLaunchSettingsScreen = {
                router.launch(AppRoute.Tab.Settings)
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddItemContent(
    onSubmitNewItem: (String) -> Unit = {},
    onLaunchSettingsScreen: () -> Unit = {},
) {
    var newItemValue by rememberSaveable { mutableStateOf("") }
    val isAddEnabled by remember {
        derivedStateOf { newItemValue.isNotEmpty() }
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        OutlinedTextField(
            value = newItemValue,
            label = { Text(stringResource(R.string.enter_value)) },
            singleLine = true,
            onValueChange = { newValue ->
                newItemValue = newValue
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            enabled = isAddEnabled,
            onClick = { onSubmitNewItem(newItemValue) }
        ) {
            Text(text = stringResource(R.string.add_item))
        }
        Button(
            onClick = onLaunchSettingsScreen,
        ) {
            Text(text = "Launch Settings")
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun AddItemScreenPreview() {
    AddItemScreen()
}
