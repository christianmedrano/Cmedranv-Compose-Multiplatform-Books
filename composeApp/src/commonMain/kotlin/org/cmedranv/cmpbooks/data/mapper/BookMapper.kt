package org.cmedranv.cmpbooks.data.mapper

import org.cmedranv.cmpbooks.data.network.dto.BookDto
import org.cmedranv.cmpbooks.domain.model.Book

class BookMapper {
    fun mapDtoToDomain(dto: BookDto): Book {
        val thumbnailUrl = dto.volumeInfo.imageLinks?.smallThumbnail?.replace("http://", "https://")
            ?: dto.volumeInfo.imageLinks?.thumbnail?.replace("http://", "https://") // Fallback si smallThumbnail no existe

        val largeImageUrl = dto.volumeInfo.imageLinks?.large?.replace("http://", "https://")
            ?: dto.volumeInfo.imageLinks?.thumbnail?.replace("http://", "https://")

        return Book(
            id = dto.id,
            title = dto.volumeInfo.title,
            authors = dto.volumeInfo.authors,
            description = dto.volumeInfo.description,
            thumbnailUrl = thumbnailUrl,
            largeImageUrl = largeImageUrl
        )
    }
}