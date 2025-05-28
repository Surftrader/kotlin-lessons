package ua.com.poseal.helloworld.ui.screens.items

import androidx.lifecycle.ViewModel
import ua.com.poseal.helloworld.ItemsRepository
import ua.com.poseal.helloworld.ui.screens.item.ItemScreenArgs
import ua.com.poseal.helloworld.ui.screens.item.ItemScreenResponse

class ItemsViewModel(
    private val repository: ItemsRepository = ItemsRepository.get()
) : ViewModel() {

    val itemFlow = repository.getItems()

    fun processResponse(response: ItemScreenResponse) {
        when (response.args) {
            is ItemScreenArgs.Add -> repository.addItem(response.newValue)
            is ItemScreenArgs.Edit -> repository.updateItem(response.args.index, response.newValue)
        }
    }
}
