package com.suryasa.made.core.domain.usecase

import com.suryasa.made.core.domain.model.Movie
import com.suryasa.made.core.domain.repository.IMovieRepository

class MovieInteractor(private val movieRepository: IMovieRepository): MovieUseCase {
    override fun getAllMovies() = movieRepository.getAllMovies()

    override fun getMovie(id: String) = movieRepository.getMovie(id)

    override fun getFavoriteMovies() = movieRepository.getFavoriteMovies()

    override fun checkMovieFavorite(id: String) = movieRepository.checkMovieFavorite(id)

    override fun setFavoriteMovie(movie: Movie, state: Boolean) = movieRepository.setFavoriteMovie(movie, state)
}