package com.example.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.model.Movie
import com.example.core.domain.model.TvShow
import com.example.core.domain.usecase.MovieUsecase

class FavoriteViewModel (private val movieUsecase: MovieUsecase): ViewModel() {
    fun getListShowFavorite(): LiveData<List<TvShow>> = movieUsecase.getListShowFavorite().asLiveData()

    fun getListMovieFavorite(): LiveData<List<Movie>> = movieUsecase.getListFavorite().asLiveData()

    fun getMovieById(movieId: Int): LiveData<List<Movie>> = movieUsecase.getMovieById(movieId).asLiveData()

    fun addToFavorite(movieFavorite : Movie){
        val newState = movieFavorite.favorite
        movieUsecase.setMovieFavorite(movieFavorite, newState)
    }

    fun getShowById(showId: Int): LiveData<List<TvShow>> = movieUsecase.getShowById(showId).asLiveData()

    fun addShowToFavorite(showFavorite: TvShow){
        val newState = showFavorite.favorite
        movieUsecase.setShowFavorite(showFavorite, newState)
    }


}