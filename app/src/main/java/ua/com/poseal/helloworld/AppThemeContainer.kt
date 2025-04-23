package ua.com.poseal.helloworld

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ua.com.poseal.helloworld.controller.AppThemeController
import ua.com.poseal.helloworld.controller.EmptyThemeController
import ua.com.poseal.helloworld.controller.RealThemeController
import ua.com.poseal.helloworld.data.AppTheme
import ua.com.poseal.helloworld.data.SharedPreferencesThemeDataSource

val LocalAppTheme = compositionLocalOf<AppTheme> {
    AppTheme.Light
}

val LocalAppThemeController = staticCompositionLocalOf<AppThemeController> {
    EmptyThemeController
}

@Composable
fun AppThemeContainer(context: @Composable () -> Unit) {
    val context = LocalContext.current
    val themeDataSource = remember {
        SharedPreferencesThemeDataSource(context)
    }
    val controller = remember {
        RealThemeController(themeDataSource)
    }
    val theme by themeDataSource.themeStateFlow.collectAsStateWithLifecycle()
    CompositionLocalProvider(
        LocalAppTheme provides theme,
        LocalAppThemeController provides controller,
    ) {
        context()
    }
}
