package ua.ilyadreamix.rootinspector.common.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalLayoutDirection

@Composable
operator fun PaddingValues.plus(value: PaddingValues): PaddingValues {
    val layoutDirection = LocalLayoutDirection.current
    return PaddingValues(
        start = calculateStartPadding(layoutDirection) + value.calculateStartPadding(layoutDirection),
        end = calculateEndPadding(layoutDirection) + value.calculateEndPadding(layoutDirection),
        top = calculateTopPadding() + value.calculateTopPadding(),
        bottom = calculateBottomPadding() + value.calculateBottomPadding()
    )
}

val PaddingValues.withNavBarInsets @Composable get() =
    WindowInsets.navigationBars.asPaddingValues() + this
