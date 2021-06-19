package com.example.moviecatalogue.di

import com.example.core.domain.usecase.MovieInteractor
import com.example.core.domain.usecase.MovieUsecase
import com.example.moviecatalogue.ui.movie.MovieViewModel
import com.example.moviecatalogue.ui.tvshow.TvShowViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUsecase> { MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel{ MovieViewModel(get()) }
    viewModel { TvShowViewModel(get()) }

}