package org.cmedranv.cmpbooks.data.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class SearchResponse(
    val kind: String,
    val totalItems: Int,
    val items: List<BookDto>? = null
)