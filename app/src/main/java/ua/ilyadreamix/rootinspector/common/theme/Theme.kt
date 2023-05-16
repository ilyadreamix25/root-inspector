@file:Suppress("PrivatePropertyName")

package ua.ilyadreamix.rootinspector.common.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

@Stable
private val DarkColorScheme = darkColorScheme(
    primary = PrimaryDark,
    secondary = SecondaryDark,
    tertiary = TertiaryDark
)

@Stable
private val LightColorScheme = lightColorScheme(
    primary = PrimaryLight,
    secondary = SecondaryLight,
    tertiary = TertiaryLight
)

@Composable
fun RootInspectorTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    useDynamicColors: Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S,
    useEdgeToEdgeScreen: Boolean = true,
    content: @Composable () -> Unit = {}
) {
    val context = LocalContext.current
    val view = LocalView.current
    val activity = context as Activity

    val colorScheme = when {
        useDynamicColors && useDarkTheme -> dynamicDarkColorScheme(context)
        useDynamicColors && !useDarkTheme -> dynamicLightColorScheme(context)
        useDarkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    if (useEdgeToEdgeScreen && !view.isInEditMode) {
        SideEffect {
            val window = activity.window.apply {
                statusBarColor = Color.Transparent.toArgb()
                navigationBarColor = Color.Transparent.toArgb()
            }

            WindowCompat.setDecorFitsSystemWindows(window, false)
            WindowCompat.getInsetsController(window, view).apply {
                isAppearanceLightNavigationBars = !useDarkTheme
                isAppearanceLightStatusBars = !useDarkTheme
            }
        }
    }

    MaterialTheme(colorScheme = colorScheme, content = content)
}
