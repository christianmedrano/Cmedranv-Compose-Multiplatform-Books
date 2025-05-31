package org.cmedranv.cmpbooks.data.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class BookDto(
    val kind: String,
    val id: String,
    val etag: String,
    val selfLink: String,
    val volumeInfo: VolumeInfo,
    val saleInfo: SaleInfo? = null,
    val accessInfo: AccessInfo? = null
)