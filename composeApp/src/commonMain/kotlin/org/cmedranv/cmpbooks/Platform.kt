package org.cmedranv.cmpbooks

/*import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle*/

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

interface HtmlText {
    val html: String
}
expect fun getHtmlText(html: String): String

/*@Composable
expect fun HtmlText(
    html: String,
    modifier: Modifier = Modifier,
    style: TextStyle = TextStyle.Default
)*/