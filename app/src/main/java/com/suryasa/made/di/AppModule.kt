package com.suryasa.made.di

import com.suryasa.made.core.domain.usecase.MovieInteractor
import com.suryasa.made.core.domain.usecase.MovieUseCase
import com.suryasa.made.detail.DetailViewModel
import com.suryasa.made.favorite.FavoriteViewModel
import com.suryasa.made.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}