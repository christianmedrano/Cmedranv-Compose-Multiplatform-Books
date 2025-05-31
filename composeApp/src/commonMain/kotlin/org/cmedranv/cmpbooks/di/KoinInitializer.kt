package org.cmedranv.cmpbooks.di

import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(appModule, dataModule, domainModule, presentationModule)
    }
}