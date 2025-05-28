package ua.com.poseal.helloworld.ui.screens.item

import androidx.lifecycle.ViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import ua.com.poseal.helloworld.ItemsRepository

@HiltViewModel(assistedFactory = ItemViewModel.Factory::class)
class ItemViewModel @AssistedInject constructor(
    @Assisted private val args: ItemScreenArgs,
    private val repository: ItemsRepository,
) : ViewModel() {

    fun getInitialValue() : String {
        return when (args) {
            is ItemScreenArgs.Add -> ""
            is ItemScreenArgs.Edit -> repository.getItems().value[args.index]
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(args: ItemScreenArgs) : ItemViewModel
    }
}
