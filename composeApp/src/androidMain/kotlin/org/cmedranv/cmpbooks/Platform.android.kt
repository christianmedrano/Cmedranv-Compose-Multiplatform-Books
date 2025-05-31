package org.cmedranv.cmpbooks

import android.os.Build
import android.text.Html

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

actual fun getHtmlText(html: String): String {
    return Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT).toString()
}