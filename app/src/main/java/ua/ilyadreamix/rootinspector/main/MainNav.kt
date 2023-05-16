package ua.ilyadreamix.rootinspector.main

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ua.ilyadreamix.rootinspector.R
import ua.ilyadreamix.rootinspector.features.all.AllFeaturesScreen
import ua.ilyadreamix.rootinspector.features.root.RootTestsScreen
import ua.ilyadreamix.rootinspector.features.summary.SummaryScreen
import ua.ilyadreamix.rootinspector.features.systemintegrity.SystemIntegrityTestsScreen

sealed class MainNavRoutes(
    val route: String,
    @StringRes val titleRes: Int,
    val showBackNavButton: Boolean = true
) {
    object AllTests : MainNavRoutes("allTests", R.string.all_tests, false)
    object RootTests : MainNavRoutes("rootTests", R.string.root_tests)
    object SafetyNetTests : MainNavRoutes("safetyNetTests", R.string.safety_net_tests)
    object XposedTests : MainNavRoutes("xposedTests", R.string.xposed_tests)
    object SystemIntegrity : MainNavRoutes("systemIntegrity", R.string.system_integrity)
    object AboutDevice : MainNavRoutes("aboutDevice", R.string.about_device)
    object Summary : MainNavRoutes("summary", R.string.summary)

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
fun MainNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = MainNavRoutes.AllTests.route,
        modifier = modifier
    ) {
        composable(route = MainNavRoutes.AllTests.route) {
            AllFeaturesScreen(navController = navController)
        }

        composable(route = MainNavRoutes.RootTests.route) {
            RootTestsScreen()
        }

        composable(route = MainNavRoutes.SafetyNetTests.route) {
            // ...
        }

        composable(route = MainNavRoutes.XposedTests.route) {
            // ...
        }

        composable(route = MainNavRoutes.SystemIntegrity.route) {
            SystemIntegrityTestsScreen()
        }

        composable(route = MainNavRoutes.AboutDevice.route) {
            // ...
        }

        composable(route = MainNavRoutes.Summary.route) {
            SummaryScreen()
        }
    }
}
