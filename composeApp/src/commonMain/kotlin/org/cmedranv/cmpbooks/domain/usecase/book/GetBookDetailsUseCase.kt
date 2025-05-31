package org.cmedranv.cmpbooks.domain.usecase.book

import org.cmedranv.cmpbooks.domain.model.Book
import org.cmedranv.cmpbooks.domain.repository.BooksRepository

class GetBookDetailsUseCase(private val booksRepository: BooksRepository) {
    suspend operator fun invoke(bookId: String): Result<Book> {
        return booksRepository.getBookDetails(bookId)
    }
}