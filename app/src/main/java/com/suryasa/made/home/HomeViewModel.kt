package com.suryasa.made.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.suryasa.made.core.domain.usecase.MovieUseCase

class HomeViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val movies = movieUseCase.getAllMovies().asLiveData()
}