package ua.com.poseal.helloworld

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import javax.inject.Singleton

interface ItemsRepository {

    fun getItems(): StateFlow<List<String>>

    fun updateItem(index: Int, newValue: String)

    fun addItem(item: String)

    fun clear()
// old version (without hilt)
//    companion object {
//        fun get(): ItemsRepository = ItemsRepositoryImpl
//    }
}

@Singleton
class ItemsRepositoryImpl @Inject constructor() : ItemsRepository {

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
