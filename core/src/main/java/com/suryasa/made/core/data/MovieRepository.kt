package com.suryasa.made.core.data

import com.suryasa.made.core.data.source.local.LocalDataSource
import com.suryasa.made.core.data.source.remote.RemoteDataSource
import com.suryasa.made.core.data.source.remote.network.ApiResponse
import com.suryasa.made.core.data.source.remote.response.MovieResponse
import com.suryasa.made.core.domain.model.Movie
import com.suryasa.made.core.domain.repository.IMovieRepository
import com.suryasa.made.core.utils.AppExecutors
import com.suryasa.made.core.utils.DataMapper
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MovieRepository(
        private val remoteDataSource: RemoteDataSource,
        private val localDataSource: LocalDataSource,
        private val appExecutors: AppExecutors
) : IMovieRepository {
    override fun getAllMovies(): Flow<Resource<List<Movie>>> =
            object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
                override fun loadFromDB(): Flow<List<Movie>> {
                    return localDataSource.getAllMovies().map {
                        DataMapper.mapEntitiesToDomain(it)
                    }
                }

                override fun shouldFetch(data: List<Movie>?): Boolean = data == null || data.isEmpty()

                override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> = remoteDataSource.getAllMovies()

                override suspend fun saveCallResult(data: List<MovieResponse>) {
                    val movieList = DataMapper.mapResponsesToEntities(data)
                    GlobalScope.launch { localDataSource.insertMovie(movieList) }
                }
            }.asFlow()

    override fun getMovie(id: String): Flow<Resource<Movie>> =
            object : NetworkBoundResource<Movie, List<MovieResponse>>() {
                override fun loadFromDB(): Flow<Movie> {
                    return localDataSource.getMovie(id).map {
                        DataMapper.mapEntityToDomain(it)
                    }
                }

                override fun shouldFetch(data: Movie?): Boolean = data == null

                override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> = remoteDataSource.getAllMovies()

                override suspend fun saveCallResult(data: List<MovieResponse>) {
                    val movieList = DataMapper.mapResponsesToEntities(data)
                    localDataSource.insertMovie(movieList)
                }
            }.asFlow()

    override fun getFavoriteMovies(): Flow<List<Movie>> {
            return localDataSource.getFavoriteMovies().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override fun checkMovieFavorite(id: String): Flow<Boolean> = localDataSource.checkMovieFavorite(id)

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movieEntity, state) }
    }
}