package org.cmedranv.cmpbooks

/*import android.text.Html
import android.widget.TextView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.viewinterop.AndroidView


@Composable
actual fun HtmlText(
    html: String,
    modifier: Modifier,
    style: TextStyle
) {
    // Usamos AndroidView para incorporar un TextView nativo de Android
    AndroidView(
        modifier = modifier,
        factory = { context ->
            TextView(context).apply {

                setTextSize(android.util.TypedValue.COMPLEX_UNIT_SP, style.fontSize.value)
                setTextColor(style.color.toArgb())
            }
        },
        update = { textView ->
            textView.text = Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT)
        }
    )
}*/