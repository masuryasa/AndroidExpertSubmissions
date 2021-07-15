package com.suryasa.made.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.suryasa.made.core.domain.usecase.MovieUseCase

class FavoriteViewModel(movieUseCase: MovieUseCase): ViewModel() {
    val favoriteMovies = movieUseCase.getFavoriteMovies().asLiveData()
}