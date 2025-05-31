package org.cmedranv.cmpbooks.domain.repository

import org.cmedranv.cmpbooks.domain.model.Book

interface BooksRepository {
    suspend fun searchBooks(query: String): Result<List<Book>>
    suspend fun getBookDetails(bookId: String): Result<Book>
}