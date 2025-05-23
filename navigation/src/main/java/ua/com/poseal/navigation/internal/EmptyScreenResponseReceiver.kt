package ua.com.poseal.navigation.internal

import ua.com.poseal.navigation.ScreenResponseReceiver
import kotlin.reflect.KClass

internal object EmptyScreenResponseReceiver : ScreenResponseReceiver {
    override fun <T : Any> get(clazz: KClass<T>): T? = null
}
