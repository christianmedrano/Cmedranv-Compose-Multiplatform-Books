package org.cmedranv.cmpbooks.util

import org.cmedranv.cmpbooks.getPlatform

class Greeting {
    private val platform = getPlatform()

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }
}