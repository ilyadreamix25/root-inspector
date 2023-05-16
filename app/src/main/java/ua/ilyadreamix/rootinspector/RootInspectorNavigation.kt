package ua.ilyadreamix.rootinspector

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ua.ilyadreamix.rootinspector.features.all.AllFeaturesScreen
import ua.ilyadreamix.rootinspector.features.root.RootTestsScreen
import ua.ilyadreamix.rootinspector.features.summary.SummaryScreen
import ua.ilyadreamix.rootinspector.features.systemintegrity.SystemIntegrityTestsScreen

sealed class RootInspectorNavRoutes(
    val route: String,
    @StringRes val titleRes: Int,
    val showBackNavButton: Boolean = true
) {
    object AllTests : RootInspectorNavRoutes("allTests", R.string.all_tests, false)
    object RootTests : RootInspectorNavRoutes("rootTests", R.string.root_tests)
    object SafetyNetTests : RootInspectorNavRoutes("safetyNetTests", R.string.safety_net_tests)
    object XposedTests : RootInspectorNavRoutes("xposedTests", R.string.xposed_tests)
    object SystemIntegrity : RootInspectorNavRoutes("systemIntegrity", R.string.system_integrity)
    object AboutDevice : RootInspectorNavRoutes("aboutDevice", R.string.about_device)
    object Summary : RootInspectorNavRoutes("summary", R.string.summary)

    companion object {
        private val asList = listOf(
            AllTests,
            RootTests,
            SafetyNetTests,
            XposedTests,
            SystemIntegrity,
            AboutDevice,
            Summary
        )

        fun findByRoute(route: String? = null) =
            asList.find { it.route == route }
    }
}

@Composable
fun RootInspectorNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = RootInspectorNavRoutes.AllTests.route,
        modifier = modifier
    ) {
        composable(route = RootInspectorNavRoutes.AllTests.route) {
            AllFeaturesScreen(navController = navController)
        }

        composable(route = RootInspectorNavRoutes.RootTests.route) {
            RootTestsScreen()
        }

        composable(route = RootInspectorNavRoutes.SafetyNetTests.route) {
            // ...
        }

        composable(route = RootInspectorNavRoutes.XposedTests.route) {
            // ...
        }

        composable(route = RootInspectorNavRoutes.SystemIntegrity.route) {
            SystemIntegrityTestsScreen()
        }

        composable(route = RootInspectorNavRoutes.AboutDevice.route) {
            // ...
        }

        composable(route = RootInspectorNavRoutes.Summary.route) {
            SummaryScreen()
        }
    }
}
