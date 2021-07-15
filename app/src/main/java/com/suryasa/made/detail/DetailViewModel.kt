package com.suryasa.made.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.suryasa.made.core.data.MovieRepository
import com.suryasa.made.core.data.Resource
import com.suryasa.made.core.data.source.local.entity.MovieEntity
import com.suryasa.made.core.domain.model.Movie
import com.suryasa.made.core.domain.usecase.MovieUseCase

class DetailViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {
    private lateinit var id: String

    fun setSelectedMovie(movieId: String) {
        this.id = movieId
    }

    fun getMovie(): LiveData<Resource<Movie>> = movieUseCase.getMovie(id).asLiveData()

    fun checkMovieFavorite(id: String): LiveData<Boolean> = movieUseCase.checkMovieFavorite(id).asLiveData()

    fun setFavoriteMovie(movie: Movie, status: Boolean) = movieUseCase.setFavoriteMovie(movie, status)
}