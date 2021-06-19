package com.example.core.domain.usecase

import com.example.core.data.Resource
import com.example.core.domain.model.Movie
import com.example.core.domain.model.TvShow
import com.example.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow

class MovieInteractor (private val IMovieRepository: IMovieRepository): MovieUsecase {
    override fun getMovies(): Flow<Resource<List<Movie>>> {
        return IMovieRepository.getMovies()
    }

    override fun getShows(): Flow<Resource<List<TvShow>>> {
        return IMovieRepository.getShows()
    }

    override fun setMovieFavorite(movieEntity: Movie, state: Boolean) {
        IMovieRepository.setMovieFavorite(movieEntity, state)
    }

    override fun setShowFavorite(tvShowEntity: TvShow, state: Boolean) {
        IMovieRepository.setShowFavorite(tvShowEntity, state)
    }

    override fun getListFavorite(): Flow<List<Movie>> {
        return IMovieRepository.getListFavorite()
    }

    override fun getListShowFavorite(): Flow<List<TvShow>> {
        return IMovieRepository.getListShowFavorite()
    }

    override fun getMovieById(movieId: Int): Flow<List<Movie>> {
        return IMovieRepository.getMovieById(movieId)
    }

    override fun getShowById(showId: Int): Flow<List<TvShow>> {
        return IMovieRepository.getShowById(showId)
    }
}