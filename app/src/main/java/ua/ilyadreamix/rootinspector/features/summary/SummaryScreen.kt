package ua.ilyadreamix.rootinspector.features.summary

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import org.json.JSONObject
import ua.ilyadreamix.rootinspector.R
import ua.ilyadreamix.rootinspector.common.components.withNavBarInsets
import ua.ilyadreamix.rootinspector.common.features.testResultExportButtons
import ua.ilyadreamix.rootinspector.common.features.toClipboardVersion
import ua.ilyadreamix.rootinspector.common.features.toJsonVersion
import ua.ilyadreamix.rootinspector.common.theme.CurrentDimen
import ua.ilyadreamix.rootinspector.features.root.RootTests
import ua.ilyadreamix.rootinspector.features.root.rootTestsContent
import ua.ilyadreamix.rootinspector.features.systemintegrity.SystemIntegrityTests
import ua.ilyadreamix.rootinspector.features.systemintegrity.systemIntegrityContent

@Composable
fun SummaryScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val rootFeature = RootTests(context)
    val systemIntegrityFeature = SystemIntegrityTests(context)

    val rootTestsResult = remember { mutableStateOf(rootFeature.checkRootAccess()) }
    val systemIntegrityTestsResult = remember { mutableStateOf(systemIntegrityFeature.checkSystemIntegrity()) }

    val rootTestsResultClipboardVersion = rootTestsResult.value.toClipboardVersion()
    val rootTestsResultJsonVersion = rootTestsResult.value.toJsonVersion()
    val systemIntegrityTestsResultClipboardVersion = systemIntegrityTestsResult.value.toClipboardVersion()
    val systemIntegrityTestsResultJsonVersion = systemIntegrityTestsResult.value.toJsonVersion()

    LazyColumn(
        modifier = modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.spacedBy(CurrentDimen.allTests.itemOuterSpacing),
        contentPadding = CurrentDimen.core.screenPadding.withNavBarInsets
    ) {
        testResultExportButtons(
            rootTestsResultClipboardVersion + systemIntegrityTestsResultClipboardVersion,
            JSONObject(
                mapOf(
                    "rootTests" to rootTestsResultJsonVersion.first,
                    "systemIntegrityTests" to systemIntegrityTestsResultJsonVersion.first
                )
            ).toString()
        )

        summaryScreenTitle(R.string.root_tests)
        rootTestsContent(rootTestsResult.value)

        summaryScreenTitle(R.string.system_integrity)
        systemIntegrityContent(systemIntegrityTestsResult.value)
    }
}

private fun LazyListScope.summaryScreenTitle(@StringRes titleRes: Int) {
    item(key = titleRes) {
        Text(
            text = stringResource(titleRes),
            modifier = Modifier
                .padding(start = CurrentDimen.allTests.itemCorners)
                .alpha(.75f)
        )
    }
}
