package org.cmedranv.cmpbooks.data.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class VolumeInfo(
    val title: String,
    val subtitle: String? = null,
    val authors: List<String>? = null,
    val publisher: String? = null,
    val publishedDate: String? = null,
    val description: String? = null,
    val industryIdentifiers: List<IndustryIdentifier>? = null,
    val readingModes: Map<String, Boolean>? = null,
    val pageCount: Int? = null,
    val printType: String? = null,
    val categories: List<String>? = null,
    val averageRating: Double? = null,
    val ratingsCount: Int? = null,
    val maturityRating: String? = null,
    val allowAnonLogging: Boolean? = null,
    val contentVersion: String? = null,
    val imageLinks: ImageLinks? = null,
    val language: String? = null,
    val previewLink: String? = null,
    val infoLink: String? = null,
    val canonicalVolumeLink: String? = null
)