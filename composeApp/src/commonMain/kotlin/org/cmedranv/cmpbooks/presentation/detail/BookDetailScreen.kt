package org.cmedranv.cmpbooks.presentation.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.network.NetworkHeaders
import coil3.network.httpHeaders
import coil3.request.ImageRequest
import kotlinx.serialization.Serializable
import org.cmedranv.cmpbooks.presentation.common.AppTopBar
import org.cmedranv.cmpbooks.presentation.common.ErrorMessage
import org.cmedranv.cmpbooks.presentation.common.FullScreenLoading
import org.cmedranv.cmpbooks.util.HtmlText
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Serializable
data class BookDetailScreen(val bookId: String)

@Composable
fun BookDetailScreen(
    bookId: String?,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: BookDetailViewModel = koinViewModel(parameters = { parametersOf(bookId) })
) {
    val context = LocalPlatformContext.current

    Scaffold(
        topBar = {
            AppTopBar(title = "Detalle del Libro", showBackButton = true, onBackClick = onBackClick)
        }
    ) { paddingValues ->
        when {
            viewModel.isLoading -> FullScreenLoading()
            viewModel.error != null -> ErrorMessage(viewModel.error!!)
            viewModel.book != null -> {
                val book = viewModel.book!!
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    book.largeImageUrl?.let { url ->
                        AsyncImage(
                            model = url,
                            contentDescription = "Book Cover",
                            modifier = Modifier
                                .fillMaxWidth(0.6f)
                                .height(250.dp),
                            contentScale = ContentScale.Fit
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = book.title, style = MaterialTheme.typography.headlineLarge)
                    book.authors?.joinToString(", ")?.let {
                        Text(text = it, style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f))
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    book.description?.let {
                        //val htmlDescription = getHtmlText(it)
                        //Text(text = htmlDescription, style = MaterialTheme.typography.bodySmall)
                        //HtmlText(html = it, style = MaterialTheme.typography.bodySmall)
                        Text(text = HtmlText(it).getText(), style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
            else -> {
                Text("No se pudo cargar la información del libro.", modifier = Modifier.padding(16.dp))
            }
        }
    }
}