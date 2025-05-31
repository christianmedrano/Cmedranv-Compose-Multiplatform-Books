package org.cmedranv.cmpbooks.presentation.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.cmedranv.cmpbooks.domain.model.Book
import org.cmedranv.cmpbooks.domain.usecase.book.GetBookDetailsUseCase

class BookDetailViewModel(
    private val bookId: String?,
    private val getBookDetailsUseCase: GetBookDetailsUseCase
) : ViewModel() {

    var book by mutableStateOf<Book?>(null)
        private set

    var isLoading by mutableStateOf(false)
        private set

    var error by mutableStateOf<String?>(null)
        private set

    init {
        bookId?.let {
            loadBookDetails(it)
        } ?: run {
            error = "ID de libro no proporcionado."
        }
    }

    private fun loadBookDetails(id: String) {
        isLoading = true
        error = null
        viewModelScope.launch {
            val result = getBookDetailsUseCase(id)
            result.onSuccess {
                book = it
            }.onFailure { e ->
                error = e.message ?: "Error al cargar los detalles del libro."
            }
            isLoading = false
        }
    }
}