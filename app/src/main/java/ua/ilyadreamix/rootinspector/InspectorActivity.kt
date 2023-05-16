package ua.ilyadreamix.rootinspector

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import ua.ilyadreamix.rootinspector.common.theme.RootInspectorTheme
import ua.ilyadreamix.rootinspector.main.MainScreen

class InspectorActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RootInspectorTheme {
                MainScreen(navController = rememberNavController())
            }
        }
    }
}
