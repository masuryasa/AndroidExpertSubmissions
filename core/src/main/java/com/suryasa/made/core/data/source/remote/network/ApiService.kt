package com.suryasa.made.core.data.source.remote.network

import com.suryasa.made.core.data.source.remote.response.ListMovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface ApiService {
    @GET("movie/popular")
    suspend fun getPopularMovies(): ListMovieResponse
}