package org.cmedranv.cmpbooks.domain.usecase.book

import org.cmedranv.cmpbooks.domain.model.Book
import org.cmedranv.cmpbooks.domain.repository.BooksRepository

class SearchBooksUseCase(private val booksRepository: BooksRepository) {
    suspend operator fun invoke(query: String): Result<List<Book>> {
        if (query.isBlank()) return Result.success(emptyList())
        return booksRepository.searchBooks(query)
    }
}