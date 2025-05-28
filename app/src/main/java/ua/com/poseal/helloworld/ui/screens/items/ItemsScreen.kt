package ua.com.poseal.helloworld.ui.screens.items

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ua.com.poseal.helloworld.R
import ua.com.poseal.helloworld.di.injectViewModel
import ua.com.poseal.helloworld.ui.AppRoute
import ua.com.poseal.helloworld.ui.AppScreen
import ua.com.poseal.helloworld.ui.AppScreenEnvironment
import ua.com.poseal.helloworld.ui.FloatingAction
import ua.com.poseal.helloworld.ui.screens.item.ItemScreenArgs
import ua.com.poseal.navigation.LocalRouter
import ua.com.poseal.navigation.ResponseListener
import ua.com.poseal.navigation.Router

val ItemsScreenProducer = { ItemsScreen() }

class ItemsScreen : AppScreen {
    private var router: Router? = null

    override val environment = AppScreenEnvironment().apply {
        titleRes = R.string.items
        icon = Icons.AutoMirrored.Filled.List
        floatingAction = FloatingAction(
            icon = Icons.Default.Add,
            onClick = {
                router?.launch(AppRoute.Item(ItemScreenArgs.Add))
            },
        )
    }

    @Composable
    override fun Content() {
        router = LocalRouter.current
        val viewModel = injectViewModel<ItemsViewModel>()
        val items by viewModel.itemFlow.collectAsStateWithLifecycle()
        val isEmpty by remember {
            derivedStateOf { items.isEmpty() }
        }
        ResponseListener(viewModel::processResponse)
        ItemsContent(
            isItemsEmpty = isEmpty,
            items = { items },
            onItemClicked = { index ->
                router?.launch(AppRoute.Item(ItemScreenArgs.Edit(index)))
            }
        )
    }
}

@Composable
fun ItemsContent(
    isItemsEmpty: Boolean,
    items: () -> List<String>,
    onItemClicked: (Int) -> Unit,
) {
    if (isItemsEmpty) {
        Text(
            text = stringResource(R.string.no_items),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentHeight()
        )
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
        ) {
            val itemsList = items()
            items(itemsList.size) { index ->
                Text(
                    text = itemsList[index],
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onItemClicked(index)
                        }
                        .padding(all = 8.dp),
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun ItemsScreenPreview() {
    ItemsScreen()
}
