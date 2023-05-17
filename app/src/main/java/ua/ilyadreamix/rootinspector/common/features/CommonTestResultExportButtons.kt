package ua.ilyadreamix.rootinspector.common.features

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import ua.ilyadreamix.rootinspector.R
import ua.ilyadreamix.rootinspector.common.theme.CurrentDimen

fun LazyListScope.testResultExportButtons(
    text: String,
    jsonText: String
) {
    item {
        val context = LocalContext.current
        val clipboardManager = LocalClipboardManager.current

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(CurrentDimen.allTests.itemOuterSpacing)
        ) {
            Button(
                onClick = {
                    clipboardManager.setText(AnnotatedString(text))
                    Toast.makeText(
                        context,
                        R.string.copied_to_clipboard,
                        Toast.LENGTH_SHORT
                    ).show()
                },
                modifier = Modifier.weight(0.5f),
                shape = RoundedCornerShape(CurrentDimen.allTests.itemCorners)
            ) {
                Text(text = stringResource(R.string.export))
            }

            Button(
                onClick = {
                    clipboardManager.setText(AnnotatedString(jsonText))
                    Toast.makeText(
                        context,
                        R.string.copied_to_clipboard,
                        Toast.LENGTH_SHORT
                    ).show()
                },
                modifier = Modifier.weight(0.5f),
                shape = RoundedCornerShape(CurrentDimen.allTests.itemCorners)
            ) {
                Text(text = stringResource(R.string.export_json))
            }
        }
    }
}
