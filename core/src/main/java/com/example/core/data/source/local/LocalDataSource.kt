package com.example.core.data.source.local

import com.example.core.data.source.local.entity.MovieEntity
import com.example.core.data.source.local.entity.TvShowEntity
import com.example.core.data.source.local.room.MovieFavoriteDao
import com.example.core.data.source.local.room.ShowFavoriteDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(
    private val movieFavoriteDao: MovieFavoriteDao,
    private val showFavoriteDao: ShowFavoriteDao
){
    suspend fun insert(movieEntity: List<MovieEntity>) {
        movieFavoriteDao.insert(movieEntity)
    }

    fun getAllMovieFavorite(): Flow<List<MovieEntity>> = movieFavoriteDao.getAllMovieFavorite()

    fun setFavorite(movie: MovieEntity, newState: Boolean){
        movie.favorite = newState
        movieFavoriteDao.updateMovie(movie)
    }

    fun setShowFavorite(tvShow: TvShowEntity, newState: Boolean){
        tvShow.favorite = newState
        showFavoriteDao.updateShow(tvShow)
    }

    fun getAllShowFavorite(): Flow<List<TvShowEntity>> = showFavoriteDao.getAllShowFavorite()

    suspend fun insertShow(tvShow: List<TvShowEntity>) = showFavoriteDao.insertShow(tvShow)

    fun getListMovieFavorite(): Flow<List<MovieEntity>> = movieFavoriteDao.getListFavoriteMovie()

    fun getListShowFavorite(): Flow<List<TvShowEntity>> = showFavoriteDao.getListShowFavorite()

    fun getMovieById(movieId: Int): Flow<List<MovieEntity>> = movieFavoriteDao.getMovieById(movieId)

    fun getShowById(showId: Int): Flow<List<TvShowEntity>> = showFavoriteDao.getShowById(showId)

}