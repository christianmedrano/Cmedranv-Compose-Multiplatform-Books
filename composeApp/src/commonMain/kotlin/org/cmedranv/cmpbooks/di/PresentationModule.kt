package org.cmedranv.cmpbooks.di

import org.cmedranv.cmpbooks.presentation.detail.BookDetailViewModel
import org.cmedranv.cmpbooks.presentation.home.HomeViewModel
import org.cmedranv.cmpbooks.presentation.login.LoginViewModel

val presentationModule = org.koin.dsl.module {
    factory { LoginViewModel(get()) }
    factory { HomeViewModel(get()) }
    factory { (bookId: String?) -> BookDetailViewModel(bookId, get()) }
}