package org.cmedranv.cmpbooks.util

import org.cmedranv.cmpbooks.getHtmlText
import org.cmedranv.cmpbooks.getPlatform

class HtmlText(
    private val html: String
) {
    private val text = getHtmlText(html)

    fun getText(): String {
        return text
    }
}