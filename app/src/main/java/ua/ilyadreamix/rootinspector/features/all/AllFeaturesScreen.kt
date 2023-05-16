package ua.ilyadreamix.rootinspector.features.all

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Extension
import androidx.compose.material.icons.rounded.Notes
import androidx.compose.material.icons.rounded.PhoneAndroid
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.Security
import androidx.compose.material.icons.rounded.Tag
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import ua.ilyadreamix.rootinspector.R
import ua.ilyadreamix.rootinspector.common.components.spacedByWithFooter
import ua.ilyadreamix.rootinspector.common.components.withNavBarInsets
import ua.ilyadreamix.rootinspector.common.theme.CurrentDimen
import ua.ilyadreamix.rootinspector.main.MainNavRoutes

val AllFeaturesInfo = listOf(
    FeatureInfo(
        titleRes = R.string.root_tests,
        descriptionRes = R.string.root_tests_description,
        imageVector = Icons.Rounded.Tag,
        navRoute = MainNavRoutes.RootTests
    ),
    FeatureInfo(
        titleRes = R.string.safety_net_tests,
        descriptionRes = R.string.safety_net_tests_description,
        imageVector = Icons.Rounded.PlayArrow,
        navRoute = MainNavRoutes.SafetyNetTests,
        iconScale = 1.1f
    ),
    FeatureInfo(
        titleRes = R.string.xposed_tests,
        descriptionRes = R.string.xposed_tests_description,
        imageVector = Icons.Rounded.Extension,
        navRoute = MainNavRoutes.XposedTests,
        iconScale = 0.8f
    ),
    FeatureInfo(
        titleRes = R.string.system_integrity,
        descriptionRes = R.string.system_integrity_description,
        imageVector = Icons.Rounded.Security,
        navRoute = MainNavRoutes.SystemIntegrity,
        iconScale = 0.8f
    ),
    FeatureInfo(
        titleRes = R.string.about_device,
        descriptionRes = R.string.about_device_description,
        imageVector = Icons.Rounded.PhoneAndroid,
        navRoute = MainNavRoutes.AboutDevice,
        iconScale = 0.8f
    ),
    FeatureInfo(
        titleRes = R.string.summary,
        descriptionRes = R.string.summary_description,
        imageVector = Icons.Rounded.Notes,
        navRoute = MainNavRoutes.Summary,
        iconScale = 0.8f
    )
)

@Suppress("DEPRECATION")
@Composable
fun AllFeaturesScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val packageManager = context.packageManager
    val appVersion = packageManager.getPackageInfo(context.packageName, 0).versionName

    LazyColumn(
        modifier = modifier.fillMaxHeight(),
        verticalArrangement = spacedByWithFooter(CurrentDimen.allTests.itemOuterSpacing),
        contentPadding = CurrentDimen.core.screenPadding.withNavBarInsets
    ) {
        items(
            items = AllFeaturesInfo,
            key = { AllFeaturesInfo.indexOf(it) }
        ) { info ->
            FeatureItem(
                info = info,
                onClick = { navController.navigate(info.navRoute.route) }
            )
        }

        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(WindowInsets.navigationBars.asPaddingValues())
                    .alpha(0.75f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CompositionLocalProvider(LocalTextStyle provides MaterialTheme.typography.bodyMedium) {
                    Text(text = stringResource(R.string.app_name_with_version, appVersion))
                    Text(text = stringResource(R.string.created_by_ilyadreamix))
                }
            }
        }
    }
}
