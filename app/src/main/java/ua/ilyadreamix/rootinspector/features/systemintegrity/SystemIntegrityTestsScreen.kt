package ua.ilyadreamix.rootinspector.features.systemintegrity

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import ua.ilyadreamix.rootinspector.R
import ua.ilyadreamix.rootinspector.common.components.withNavBarInsets
import ua.ilyadreamix.rootinspector.common.features.CommonTestResultItem
import ua.ilyadreamix.rootinspector.common.features.CommonTestResult
import ua.ilyadreamix.rootinspector.common.features.testResultExportButtons
import ua.ilyadreamix.rootinspector.common.features.toClipboardVersion
import ua.ilyadreamix.rootinspector.common.features.toJsonVersion
import ua.ilyadreamix.rootinspector.common.theme.CurrentDimen

@Composable
fun SystemIntegrityTestsScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val feature = SystemIntegrityTests(context)

    val testsResult = remember { mutableStateOf(feature.checkSystemIntegrity()) }
    val testsResultClipboardVersion = testsResult.value.toClipboardVersion()
    val testsResultJsonVersion = testsResult.value.toJsonVersion()

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(CurrentDimen.allTests.itemOuterSpacing),
        contentPadding = CurrentDimen.core.screenPadding.withNavBarInsets
    ) {
        testResultExportButtons(testsResultClipboardVersion, testsResultJsonVersion.second)
        systemIntegrityContent(testsResult = testsResult.value)
    }
}

fun LazyListScope.systemIntegrityContent(testsResult: List<CommonTestResult>) {
    items(
        items = testsResult,
        key = { it.titleRes }
    ) { detection ->
        CommonTestResultItem(
            titleRes = detection.titleRes,
            result = detection
        )
    }
}
