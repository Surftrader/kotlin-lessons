package ua.com.poseal.navigation.internal

import ua.com.poseal.navigation.ScreenResponseReceiver
import kotlin.reflect.KClass

class ScreenResponsesBus : ScreenResponseReceiver {

    private var response: Any? = null

    override fun <T : Any> get(clazz: KClass<T>): T? {
        val response = this.response
        if (response != null && clazz.isInstance(response)) {
            this.response = null
            return response as T
        }
        return null
    }

    fun send(response: Any) {
        this.response = response
    }

    fun cleanUp() {
        this.response = null
    }
}
