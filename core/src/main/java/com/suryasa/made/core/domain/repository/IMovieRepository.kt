package com.suryasa.made.core.domain.repository

import com.suryasa.made.core.data.Resource
import com.suryasa.made.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun getAllMovies(): Flow<Resource<List<Movie>>>

    fun getMovie(id: String): Flow<Resource<Movie>>

    fun getFavoriteMovies(): Flow<List<Movie>>

    fun checkMovieFavorite(id: String): Flow<Boolean>

    fun setFavoriteMovie(movie: Movie, state: Boolean)
}