package ua.com.poseal.helloworld

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

interface ItemsRepository {

    fun getItems(): StateFlow<List<String>>

    fun updateItem(index: Int, newValue: String)

    fun addItem(item: String)

    fun clear()

    companion object {
        fun get(): ItemsRepository = ItemsRepositoryImpl
    }
}

object ItemsRepositoryImpl : ItemsRepository {
    private val items = MutableStateFlow(generateFakeItems())

    override fun getItems(): StateFlow<List<String>> {
        return items
    }

    override fun updateItem(index: Int, newValue: String) {
        items.update {
            it.toMutableList().apply { set(index, newValue) }
        }
    }

    override fun addItem(item: String) {
        items.update { it + item }
    }

    override fun clear() {
        items.update { emptyList() }
    }

    private fun generateFakeItems() = List(10) {
        "Item #${it + 1}"
    }
}
