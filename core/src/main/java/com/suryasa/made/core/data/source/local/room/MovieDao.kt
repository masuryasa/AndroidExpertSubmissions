package com.suryasa.made.core.data.source.local.room

import androidx.room.*
import com.suryasa.made.core.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM popularmovies")
    fun getAllMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM popularmovies WHERE id = :id")
    fun getMovie(id: String): Flow<MovieEntity>

    @Query("SELECT * FROM popularmovies WHERE favorite = 1")
    fun getFavoriteMovies(): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: List<MovieEntity>)

    @Update
    fun updateFavoriteMovie(movie: MovieEntity)

    @Query("SELECT favorite FROM popularmovies WHERE id = :id")
    fun checkMovieFavorite(id: String): Flow<Boolean>
}