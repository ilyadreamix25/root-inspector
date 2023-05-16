package ua.ilyadreamix.rootinspector.features.root

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
fun RootTestsScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val feature = RootTests(context)
    val testsResult = remember { mutableStateOf(feature.checkRootAccess()) }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(CurrentDimen.allTests.itemOuterSpacing),
        contentPadding = CurrentDimen.core.screenPadding.withNavBarInsets
    ) {
        rootTestsContent(testsResult.value)
    }
}

fun LazyListScope.rootTestsContent(testsResult: RootTestsResult) {
    item(key = 0) {
        CommonTestResultItem(
            titleRes = R.string.su_binaries,
            result = testsResult.suBinaries
        )
    }

    item(key = 1) {
        CommonTestResultItem(
            titleRes = R.string.su_command,
            result = testsResult.suCommand
        )
    }

    item(key = 2) {
        CommonTestResultItem(
            titleRes = R.string.suspicious_values_in_path,
            result = testsResult.suspiciousValuesInPath
        )
    }

    item(key = 3) {
        CommonTestResultItem(
            titleRes = R.string.root_management_apps,
            result = testsResult.rootManagementApps
        )
    }
}
