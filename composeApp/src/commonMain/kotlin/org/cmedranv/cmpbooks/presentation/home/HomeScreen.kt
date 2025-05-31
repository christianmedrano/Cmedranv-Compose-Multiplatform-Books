package org.cmedranv.cmpbooks.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import org.cmedranv.cmpbooks.domain.model.Book
import org.cmedranv.cmpbooks.presentation.common.AppTopBar
import org.cmedranv.cmpbooks.presentation.common.ErrorMessage
import org.cmedranv.cmpbooks.presentation.common.FullScreenLoading
import org.koin.compose.viewmodel.koinViewModel
import coil3.compose.LocalPlatformContext
import coil3.network.NetworkHeaders
import coil3.network.httpHeaders
import kotlinx.serialization.Serializable

@Serializable
object HomeScreen

@Composable
fun HomeScreen(
    onBookClick: (bookId: String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinViewModel()
) {
    Scaffold(
        topBar = {
            AppTopBar(title = "BooksApp")
        },
        /*bottomBar = {
            BottomNavigation {
                BottomNavigationItem(
                    selected = true,
                    onClick = { },
                    icon = { Icon(Icons.Default.Search, contentDescription = "Search") },
                    label = { Text("Search") }
                )
            }
        }*/
    ) { paddingValues ->
        Column(
            modifier = modifier.fillMaxSize().padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SearchBar(
                query = viewModel.searchQuery,
                onQueryChange = viewModel::onSearchQueryChange,
                onSearch = viewModel::searchBooks,
                isLoading = viewModel.isLoading
            )

            when {
                viewModel.isLoading -> FullScreenLoading()
                viewModel.error != null -> ErrorMessage(viewModel.error!!)
                viewModel.books.isEmpty() && viewModel.searchQuery.isNotBlank() -> {
                    Text("No se encontraron libros.", modifier = Modifier.padding(16.dp))
                }
                viewModel.books.isNotEmpty() -> {
                    BookList(books = viewModel.books, onBookClick = onBookClick)
                }
                else -> {
                    Text("Busca tus libros favoritos.", modifier = Modifier.padding(16.dp))
                }
            }
        }
    }
}

@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: () -> Unit,
    isLoading: Boolean,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth().padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = query,
            onValueChange = onQueryChange,
            label = { Text("Buscar libros...") },
            modifier = Modifier.weight(1f),
            singleLine = true,
            trailingIcon = {
                if (isLoading) {
                    CircularProgressIndicator(modifier = Modifier.size(24.dp))
                } else {
                    IconButton(onClick = onSearch) {
                        Icon(Icons.Default.Search, contentDescription = "Search")
                    }
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = { onSearch() }
            )
        )
    }
}

@Composable
fun BookList(
    books: List<Book>,
    onBookClick: (bookId: String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(books) { book ->
            BookListItem(book = book, onClick = { onBookClick(book.id) })
        }
    }
}

@Composable
fun BookListItem(
    book: Book,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        val context = LocalPlatformContext.current
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                //model = book.thumbnailUrl,
                model = ImageRequest.Builder(context)
                    .data(book.thumbnailUrl)
                    .httpHeaders(
                        NetworkHeaders.Builder()
                            .add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.4896.75 Safari/537.36")
                            .build()
                    )
                    .build(),
                contentDescription = book.title,
                modifier = Modifier.size(60.dp, 90.dp),
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = book.title, style = MaterialTheme.typography.titleLarge)
                book.authors?.joinToString(", ")?.let {
                    Text(text = it, style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}