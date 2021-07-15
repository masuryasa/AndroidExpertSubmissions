package com.suryasa.made.core.domain.usecase

import com.suryasa.made.core.data.Resource
import com.suryasa.made.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getAllMovies(): Flow<Resource<List<Movie>>>

    fun getMovie(id: String): Flow<Resource<Movie>>

    fun getFavoriteMovies(): Flow<List<Movie>>

    fun checkMovieFavorite(id: String): Flow<Boolean>

    fun setFavoriteMovie(movie: Movie, state: Boolean)
}