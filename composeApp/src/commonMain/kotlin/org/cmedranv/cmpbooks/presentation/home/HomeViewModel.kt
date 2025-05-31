package org.cmedranv.cmpbooks.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.cmedranv.cmpbooks.domain.model.Book
import org.cmedranv.cmpbooks.domain.usecase.book.SearchBooksUseCase

class HomeViewModel(
    private val searchBooksUseCase: SearchBooksUseCase
) : ViewModel() {

    var searchQuery by mutableStateOf("")
        private set

    var books by mutableStateOf<List<Book>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    var error by mutableStateOf<String?>(null)
        private set

    fun onSearchQueryChange(newQuery: String) {
        searchQuery = newQuery
    }

    fun searchBooks() {
        if (searchQuery.isBlank()) {
            books = emptyList()
            error = null
            return
        }

        isLoading = true
        error = null
        viewModelScope.launch {
            val result = searchBooksUseCase(searchQuery)
            result.onSuccess {
                books = it
            }.onFailure { e ->
                error = e.message ?: "Error al buscar libros."
            }
            isLoading = false
        }
    }
}