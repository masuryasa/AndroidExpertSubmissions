package com.suryasa.made.core.data.source.local

import com.suryasa.made.core.data.source.local.entity.MovieEntity
import com.suryasa.made.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val movieDao: MovieDao){
    fun getAllMovies(): Flow<List<MovieEntity>> = movieDao.getAllMovies()

    fun getMovie(id: String): Flow<MovieEntity> = movieDao.getMovie(id)

    fun getFavoriteMovies(): Flow<List<MovieEntity>> = movieDao.getFavoriteMovies()

    fun insertMovie(movies: List<MovieEntity>) = movieDao.insertMovie(movies)

    fun checkMovieFavorite(id: String): Flow<Boolean> = movieDao.checkMovieFavorite(id)

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.favorite = newState
        movieDao.updateFavoriteMovie(movie)
    }
}