package org.cmedranv.cmpbooks.data.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class AccessInfo(
    val country: String? = null,
    val viewability: String? = null,
    val embeddable: Boolean? = null,
    val publicDomain: Boolean? = null,
    val textToSpeechPermission: String? = null,
    val epub: EpubInfo? = null,
    val pdf: PdfInfo? = null,
    val webReaderLink: String? = null,
    val accessViewStatus: String? = null,
    val quoteSharingAllowed: Boolean? = null
)

@Serializable
data class EpubInfo(
    val isAvailable: Boolean? = null,
    val acsTokenLink: String? = null
)

@Serializable
data class PdfInfo(
    val isAvailable: Boolean? = null,
    val acsTokenLink: String? = null
)