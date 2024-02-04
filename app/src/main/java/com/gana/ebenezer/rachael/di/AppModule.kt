package com.gana.ebenezer.rachael.di

import com.gana.ebenezer.rachael.MainViewModel
import com.gana.ebenezer.rachael.Repository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { MainViewModel(get()) }
}

val repositoryModule = module {
    single { Repository(get()) }
}
