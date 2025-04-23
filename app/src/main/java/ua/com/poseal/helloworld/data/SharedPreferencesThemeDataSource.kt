package ua.com.poseal.helloworld.data

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import kotlinx.coroutines.flow.MutableStateFlow

class SharedPreferencesThemeDataSource(
    context: Context
) : ThemeDataSource {
    private val preferences = context.getSharedPreferences(
        THEME_PREFERENCES_NAME, Context.MODE_PRIVATE
    )
    override val themeStateFlow: MutableStateFlow<AppTheme> = MutableStateFlow(readTheme())

    override fun setTheme(theme: AppTheme) {
        preferences.edit()
            .putInt(KEY_BUTTON_COLOR, theme.buttonColor.toArgb())
            .putInt(KEY_BACKGROUND_COLOR, theme.bgColor.toArgb())
            .apply()
        themeStateFlow.value = theme
    }

    private fun readTheme(): AppTheme {
        if (!hasSavedTheme()) return AppTheme.Light
        return AppTheme(
            buttonColor = Color(preferences.getInt(KEY_BUTTON_COLOR, 0)),
            bgColor = Color(preferences.getInt(KEY_BACKGROUND_COLOR, 0))
        )
    }

    private fun hasSavedTheme(): Boolean = preferences.contains(KEY_BACKGROUND_COLOR)

    private companion object {
        const val THEME_PREFERENCES_NAME = "themes"
        const val KEY_BUTTON_COLOR = "BUTTON_COLOR"
        const val KEY_BACKGROUND_COLOR = "BACKGROUND_COLOR"
    }
}
