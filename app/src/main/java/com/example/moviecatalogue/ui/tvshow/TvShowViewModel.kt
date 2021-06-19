package com.example.moviecatalogue.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.data.Resource
import com.example.core.domain.model.TvShow
import com.example.core.domain.usecase.MovieUsecase

class TvShowViewModel(private val movieUsecase: MovieUsecase): ViewModel() {
    fun getTvShowApi(): LiveData<Resource<List<TvShow>>> {
        return movieUsecase.getShows().asLiveData()
    }

    fun addToFavorite(showFavorite: TvShow){
        val newState = showFavorite.favorite
        movieUsecase.setShowFavorite(showFavorite, newState)
    }

    fun getShowById(showId: Int): LiveData<List<TvShow>> = movieUsecase.getShowById(showId).asLiveData()


}