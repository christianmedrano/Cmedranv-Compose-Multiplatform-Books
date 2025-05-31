package org.cmedranv.cmpbooks

/*import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle*/
import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()

actual fun getHtmlText(html: String): String {
    return html.replace(Regex("<[^>]*>"), "")
}

/*@Composable
actual fun HtmlText(
    html: String,
    modifier: Modifier,
    style: TextStyle
) {
    val plainText = html.replace(Regex("<[^>]*>"), "")
    Text(text = plainText, modifier = modifier, style = style)
}*/