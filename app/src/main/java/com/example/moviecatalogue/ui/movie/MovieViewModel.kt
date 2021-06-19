package com.example.moviecatalogue.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.data.Resource
import com.example.core.domain.model.Movie
import com.example.core.domain.usecase.MovieUsecase

class MovieViewModel(private val movieUsecase: MovieUsecase): ViewModel() {
    fun getMovieApi(): LiveData<Resource<List<Movie>>> {
        return movieUsecase.getMovies().asLiveData()
    }

    fun addToFavorite(movieFavorite : Movie){
        val newState = movieFavorite.favorite
        movieUsecase.setMovieFavorite(movieFavorite, newState)
    }

    fun getMovieById(movieId: Int): LiveData<List<Movie>> = movieUsecase.getMovieById(movieId).asLiveData()


}