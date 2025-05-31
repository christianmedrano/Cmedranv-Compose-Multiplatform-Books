package org.cmedranv.cmpbooks.di

import io.ktor.serialization.kotlinx.json.json
import org.cmedranv.cmpbooks.data.mapper.BookMapper
import org.cmedranv.cmpbooks.data.network.api.BooksApiClient
import org.cmedranv.cmpbooks.data.repository.AuthRepositoryImpl
import org.cmedranv.cmpbooks.data.repository.BooksRepositoryImpl
import org.cmedranv.cmpbooks.domain.repository.AuthRepository
import org.cmedranv.cmpbooks.domain.repository.BooksRepository

val dataModule = org.koin.dsl.module {
    single { BooksApiClient(get()) } // Ktor Client inyectado
    single<BooksRepository> { BooksRepositoryImpl(get(), get()) }
    single<AuthRepository> { AuthRepositoryImpl() } // Implementación ficticia
    single { BookMapper() }
    // Define el cliente Ktor aquí
    single {
        io.ktor.client.HttpClient {
            install(io.ktor.client.plugins.contentnegotiation.ContentNegotiation) {
                json(kotlinx.serialization.json.Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                    isLenient = true
                })
            }
        }
    }
}