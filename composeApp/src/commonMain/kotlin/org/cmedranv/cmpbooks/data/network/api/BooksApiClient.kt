package org.cmedranv.cmpbooks.data.network.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import org.cmedranv.cmpbooks.data.network.GoogleBooksApiConstants
import org.cmedranv.cmpbooks.data.network.dto.BookDto
import org.cmedranv.cmpbooks.data.network.dto.SearchResponse

class BooksApiClient(private val httpClient: HttpClient) {

    suspend fun searchBooks(query: String): SearchResponse {
        return httpClient.get("${GoogleBooksApiConstants.BASE_URL}volumes") {
            parameter("q", query)
            parameter("key", GoogleBooksApiConstants.API_KEY)
            parameter("maxResults", 20) // Limita los resultados
        }.body()
    }

    suspend fun getBookDetails(bookId: String): BookDto {
        return httpClient.get("${GoogleBooksApiConstants.BASE_URL}volumes/$bookId") {
            parameter("key", GoogleBooksApiConstants.API_KEY)
        }.body()
    }
}