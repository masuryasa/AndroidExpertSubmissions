package com.suryasa.made.core.data.source.remote.network

import com.suryasa.made.core.data.source.remote.response.ListMovieResponse
import retrofit2.http.GET

interface ApiService {
    @GET("movie/popular")
    suspend fun getPopularMovies(): ListMovieResponse
}