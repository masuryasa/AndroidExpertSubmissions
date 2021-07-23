package com.suryasa.made.core.utils

import com.suryasa.made.core.data.source.local.entity.MovieEntity
import com.suryasa.made.core.data.source.remote.response.MovieResponse
import com.suryasa.made.core.domain.model.Movie

object DataMapper {
    fun mapResponsesToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie =
                    it.releaseDate?.let { itRel ->
                        MovieEntity(
                                id = it.id,
                                title = it.title,
                                overview = it.overview,
                                posterUrl = it.posterUrl,
                                releaseDate = itRel,
                                rating = it.rating,
                                favorite = false
                        )
                    }
            if (movie != null) {
                movieList.add(movie)
            }
        }
        return movieList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
            input.map {
                Movie(
                        movieId = it.id,
                        title = it.title,
                        overview = it.overview,
                        posterUrl = it.posterUrl,
                        releaseDate = it.releaseDate,
                        rating = it.rating,
                        favorite = it.favorite
                )
            }

    fun mapEntityToDomain(input: MovieEntity): Movie =
            Movie(
                    movieId = input.id,
                    title = input.title,
                    overview = input.overview,
                    posterUrl = input.posterUrl,
                    releaseDate = input.releaseDate,
                    rating = input.rating,
                    favorite = input.favorite
            )

    fun mapDomainToEntity(input: Movie) = MovieEntity(
            id = input.movieId,
            title = input.title,
            overview = input.overview,
            posterUrl = input.posterUrl,
            releaseDate = input.releaseDate,
            rating = input.rating,
            favorite = input.favorite
    )
}