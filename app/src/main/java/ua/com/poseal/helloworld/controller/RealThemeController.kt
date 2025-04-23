package ua.com.poseal.helloworld.controller

import ua.com.poseal.helloworld.data.AppTheme
import ua.com.poseal.helloworld.data.ThemeDataSource

class RealThemeController (
    private val themeDataSource: ThemeDataSource,
) : AppThemeController {

    override fun toggle() {
        val currentTheme = themeDataSource.themeStateFlow.value
        if (currentTheme == AppTheme.Dark) {
            themeDataSource.setTheme(AppTheme.Light)
        } else {
            themeDataSource.setTheme(AppTheme.Dark)
        }
    }
}
