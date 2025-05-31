package org.cmedranv.cmpbooks.data.repository

import org.cmedranv.cmpbooks.data.mapper.BookMapper
import org.cmedranv.cmpbooks.data.network.api.BooksApiClient
import org.cmedranv.cmpbooks.domain.model.Book
import org.cmedranv.cmpbooks.domain.repository.BooksRepository

class BooksRepositoryImpl(
    private val apiClient: BooksApiClient,
    private val bookMapper: BookMapper
) : BooksRepository {
    override suspend fun searchBooks(query: String): Result<List<Book>> {
        return try {
            val response = apiClient.searchBooks(query)
            val books = response.items?.map { bookMapper.mapDtoToDomain(it) } ?: emptyList()
            Result.success(books)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getBookDetails(bookId: String): Result<Book> {
        return try {
            val dto = apiClient.getBookDetails(bookId)
            Result.success(bookMapper.mapDtoToDomain(dto))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}