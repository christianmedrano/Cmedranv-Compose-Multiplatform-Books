package org.cmedranv.cmpbooks.data.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class SaleInfo(
    val country: String? = null,
    val saleability: String? = null,
    val isEbook: Boolean? = null,
    val listPrice: PriceInfo? = null,
    val retailPrice: PriceInfo? = null,
    val buyLink: String? = null,
    val offers: List<Offer>? = null
)

@Serializable
data class PriceInfo(
    val amount: Double? = null,
    val currencyCode: String? = null
)

@Serializable
data class Offer(
    val finskyOfferType: Int? = null,
    val listPrice: PriceInfo? = null,
    val retailPrice: PriceInfo? = null
)