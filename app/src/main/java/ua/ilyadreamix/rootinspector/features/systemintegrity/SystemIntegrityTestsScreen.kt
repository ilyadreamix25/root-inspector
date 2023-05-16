package ua.ilyadreamix.rootinspector.features.systemintegrity

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import ua.ilyadreamix.rootinspector.R
import ua.ilyadreamix.rootinspector.common.components.withNavBarInsets
import ua.ilyadreamix.rootinspector.common.features.CommonTestResultItem
import ua.ilyadreamix.rootinspector.common.theme.CurrentDimen

@Composable
fun SystemIntegrityTestsScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val feature = SystemIntegrityTests(context)
    val testsResult = remember { mutableStateOf(feature.checkSystemIntegrity()) }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(CurrentDimen.allTests.itemOuterSpacing),
        contentPadding = CurrentDimen.core.screenPadding.withNavBarInsets
    ) {
        systemIntegrityContent(testsResult = testsResult.value)
    }
}

fun LazyListScope.systemIntegrityContent(testsResult: SystemIntegrityTestsResult) {
    item(key = 4) {
        CommonTestResultItem(
            titleRes = R.string.dangerous_apps,
            result = testsResult.dangerousApps
        )
    }

    item(key = 5) {
        CommonTestResultItem(
            titleRes = R.string.test_keys,
            result = testsResult.testKeys
        )
    }

    item(key = 6) {
        CommonTestResultItem(
            titleRes = R.string.rw_paths,
            result = testsResult.rwPaths
        )
    }
}
