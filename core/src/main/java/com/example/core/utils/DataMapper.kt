package com.example.core.utils

import com.example.core.data.source.local.entity.MovieEntity
import com.example.core.data.source.local.entity.TvShowEntity
import com.example.core.data.source.remote.response.MovieResponse
import com.example.core.data.source.remote.response.TvShowResponse
import com.example.core.domain.model.Movie
import com.example.core.domain.model.TvShow


object DataMapper {
    fun mapResponsesToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                it.id,
                it.overview,
                it.originalTitle,
                it.posterPath,
                it.releaseDate,
                it.voteAverage
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapShowResponsesToEntities(input: List<TvShowResponse>): List<TvShowEntity> {
        val showList = ArrayList<TvShowEntity>()
        input.map {
            val show = TvShowEntity(
                it.id,
                it.firstAirDate,
                it.overview,
                it.posterPath,
                it.originalName,
                it.voteAverage
            )
            showList.add(show)
        }
        return showList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                it.id,
                it.overview,
                it.originalTitle,
                it.posterPath,
                it.releaseDate,
                it.voteAverage,
                it.favorite
            )
        }

    fun mapShowEntitiesToDomain(input: List<TvShowEntity>): List<TvShow> =
        input.map {
            TvShow(
                it.id,
                it.firstAirDate,
                it.overview,
                it.posterPath,
                it.originalName,
                it.voteAverage,
                it.favorite
            )
        }

    fun mapDomainToEntities(input: Movie): MovieEntity =
        MovieEntity(
            input.id,
            input.overview,
            input.originalTitle,
            input.posterPath,
            input.releaseDate,
            input.voteAverage,
            input.favorite
        )

    fun mapShowDomainToEntites(input: TvShow): TvShowEntity =
        TvShowEntity(
            input.id,
            input.firstAirDate,
            input.overview,
            input.posterPath,
            input.originalName,
            input.voteAverage,
            input.favorite
        )
}