package com.example.core.domain.repository

import com.example.core.data.Resource
import com.example.core.domain.model.Movie
import com.example.core.domain.model.TvShow
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun getMovies(): Flow<Resource<List<Movie>>>

    fun getShows(): Flow<Resource<List<TvShow>>>

    fun setMovieFavorite(movie: Movie, state: Boolean)

    fun setShowFavorite(tvShow: TvShow, state: Boolean)

    fun getListFavorite(): Flow<List<Movie>>

    fun getListShowFavorite(): Flow<List<TvShow>>

    fun getMovieById(movieId: Int): Flow<List<Movie>>

    fun getShowById(showId: Int): Flow<List<TvShow>>
}