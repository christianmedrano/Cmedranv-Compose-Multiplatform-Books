package org.cmedranv.cmpbooks

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform