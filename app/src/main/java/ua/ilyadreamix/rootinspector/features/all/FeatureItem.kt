package ua.ilyadreamix.rootinspector.features.all

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import ua.ilyadreamix.rootinspector.common.theme.CurrentDimen
import ua.ilyadreamix.rootinspector.RootInspectorNavRoutes

data class FeatureInfo(
    @StringRes val titleRes: Int,
    @StringRes val descriptionRes: Int,
    val imageVector: ImageVector,
    val navRoute: RootInspectorNavRoutes,
    val iconScale: Float? = null
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeatureItem(
    info: FeatureInfo,
    modifier: Modifier = Modifier,
    onClick: (info: FeatureInfo) -> Unit = {}
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        onClick = { onClick(info) },
        shape = RoundedCornerShape(CurrentDimen.allTests.itemCorners)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(CurrentDimen.allTests.itemInnerSpacing),
            modifier = Modifier
                .fillMaxSize()
                .padding(CurrentDimen.allTests.itemPadding)
        ) {
            Icon(
                imageVector = info.imageVector,
                contentDescription = null,
                modifier = Modifier.scale(info.iconScale ?: 1f)
            )

            Column {
                Text(
                    text = stringResource(id = info.titleRes),
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = stringResource(id = info.descriptionRes),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
