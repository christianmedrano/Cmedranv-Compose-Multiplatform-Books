package org.cmedranv.cmpbooks.di

import org.cmedranv.cmpbooks.domain.usecase.auth.LoginUseCase
import org.cmedranv.cmpbooks.domain.usecase.book.GetBookDetailsUseCase
import org.cmedranv.cmpbooks.domain.usecase.book.SearchBooksUseCase

val domainModule = org.koin.dsl.module {
    factory { SearchBooksUseCase(get()) }
    factory { GetBookDetailsUseCase(get()) }
    factory { LoginUseCase(get()) }
}