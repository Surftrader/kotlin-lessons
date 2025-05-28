package ua.com.poseal.helloworld.ui.screens.item

import androidx.lifecycle.ViewModel
import ua.com.poseal.helloworld.ItemsRepository

class ItemViewModel(
    private val args: ItemScreenArgs,
    private val repository: ItemsRepository = ItemsRepository.get()
) : ViewModel() {

    init {
        println("AAAA ItemViewModel-${hashCode()} created")
    }

    fun getInitialValue() : String {
        return when (args) {
            is ItemScreenArgs.Add -> ""
            is ItemScreenArgs.Edit -> repository.getItems().value[args.index]
        }
    }

    override fun onCleared() {
        super.onCleared()
        println("AAAA ItemViewModel-${hashCode()} destroyed")
    }
}
