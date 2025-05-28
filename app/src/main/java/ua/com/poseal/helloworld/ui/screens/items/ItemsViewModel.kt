package ua.com.poseal.helloworld.ui.screens.items

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ua.com.poseal.helloworld.ItemsRepository
import ua.com.poseal.helloworld.ui.screens.item.ItemScreenArgs
import ua.com.poseal.helloworld.ui.screens.item.ItemScreenResponse
import javax.inject.Inject

@HiltViewModel
class ItemsViewModel @Inject constructor(
    private val repository: ItemsRepository,
) : ViewModel() {

    val itemFlow = repository.getItems()

    fun processResponse(response: ItemScreenResponse) {
        when (response.args) {
            is ItemScreenArgs.Add -> repository.addItem(response.newValue)
            is ItemScreenArgs.Edit -> repository.updateItem(response.args.index, response.newValue)
        }
    }
}
