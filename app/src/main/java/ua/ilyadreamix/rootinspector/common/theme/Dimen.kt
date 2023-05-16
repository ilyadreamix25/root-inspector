package ua.ilyadreamix.rootinspector.common.theme

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class RootInspectorDimen(
    val core: Core = Core(),
    val allTests: AllTests = AllTests(),
    val rootTests: RootTests = RootTests()
) {
    data class Core(
        val screenPadding: PaddingValues = PaddingValues(start = 16.dp, end = 16.dp)
    )

    data class AllTests(
        val itemCorners: Dp = 12.dp,
        val itemPadding: PaddingValues = PaddingValues(12.dp),
        val itemInnerSpacing: Dp = 12.dp,
        val itemOuterSpacing: Dp = 14.dp
    )

    data class RootTests(
        val itemInnerSpacing: Dp = 6.dp
    )
}

val LocalRootInspectorDimen = compositionLocalOf { RootInspectorDimen() }

val CurrentDimen @Composable get() = LocalRootInspectorDimen.current
