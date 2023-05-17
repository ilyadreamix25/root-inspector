package ua.ilyadreamix.rootinspector.common.features

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import ua.ilyadreamix.rootinspector.R
import ua.ilyadreamix.rootinspector.common.theme.CurrentDimen

@Composable
fun CommonTestResultItem(
    @StringRes titleRes: Int,
    result: CommonTestResult,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(CurrentDimen.allTests.itemCorners)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(CurrentDimen.allTests.itemPadding)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(CurrentDimen.rootTests.itemInnerSpacing),
            ) {
                Icon(
                    imageVector = if (result.detected) Icons.Rounded.Done else Icons.Rounded.Close,
                    contentDescription = null
                )
                Text(
                    text = stringResource(titleRes),
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            }

            CompositionLocalProvider(LocalTextStyle provides MaterialTheme.typography.bodyMedium) {
                if (result.detected && result.values.isNotEmpty()) {
                    result.values.forEach { detection ->
                        Text(text = detection)
                    }
                } else if (result.detected) {
                    Text(text = stringResource(id = R.string.detected))
                } else {
                    Text(text = stringResource(id = R.string.not_detected))
                }
            }
        }
    }
}
