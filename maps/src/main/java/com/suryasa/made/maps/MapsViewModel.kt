package com.suryasa.made.maps

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.suryasa.made.core.domain.usecase.MovieUseCase

class MapsViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val movie = movieUseCase.getAllMovies().asLiveData()
}