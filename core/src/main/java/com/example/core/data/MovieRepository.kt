package com.example.core.data

import com.example.core.data.source.local.LocalDataSource
import com.example.core.data.source.remote.ApiResponse
import com.example.core.data.source.remote.RemoteDataSource
import com.example.core.data.source.remote.response.MovieResponse
import com.example.core.data.source.remote.response.TvShowResponse
import com.example.core.domain.model.Movie
import com.example.core.domain.model.TvShow
import com.example.core.domain.repository.IMovieRepository
import com.example.core.utils.AppExecutors
import com.example.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

open class MovieRepository(
        private val remoteDataSource: RemoteDataSource,
        private val localDataSource: LocalDataSource,
        private val appExecutors: AppExecutors
): IMovieRepository
{
    override fun getMovies(): Flow<Resource<List<Movie>>> {
        return object : NetworkBoundSource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMovieFavorite().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean {
                return true
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> {
                return remoteDataSource.getAllMovie()
            }

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insert(movieList)
            }
        }.asFlow()
    }

    override fun getShows(): Flow<Resource<List<TvShow>>> {
        return object : NetworkBoundSource<List<TvShow>, List<TvShowResponse>>() {
            override fun loadFromDB(): Flow<List<TvShow>> {
                return localDataSource.getAllShowFavorite().map {
                    DataMapper.mapShowEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<TvShow>?): Boolean {
                return true
            }

            override suspend fun createCall(): Flow<ApiResponse<List<TvShowResponse>>> {
                return remoteDataSource.getAllTvShow()
            }

            override suspend fun saveCallResult(data: List<TvShowResponse>) {
                val showList = DataMapper.mapShowResponsesToEntities(data)
                localDataSource.insertShow(showList)
            }

        }.asFlow()
    }

    override fun setMovieFavorite(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntities(movie)
        appExecutors.diskIO().execute { localDataSource.setFavorite(movieEntity, state) }
    }

    override fun setShowFavorite(tvShow: TvShow, state: Boolean) {
        val tvShowEntity = DataMapper.mapShowDomainToEntites(tvShow)
        appExecutors.diskIO().execute { localDataSource.setShowFavorite(tvShowEntity, state) }
        }

    override fun getListFavorite(): Flow<List<Movie>> {
        return localDataSource.getListMovieFavorite().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override fun getListShowFavorite(): Flow<List<TvShow>>{
        return localDataSource.getListShowFavorite().map { DataMapper.mapShowEntitiesToDomain(it) }
    }

    override fun getMovieById(movieId: Int): Flow<List<Movie>> =
            localDataSource.getMovieById(movieId).map {
                DataMapper.mapEntitiesToDomain(it)
            }

    override fun getShowById(showId: Int): Flow<List<TvShow>> =
            localDataSource.getShowById(showId).map {
                DataMapper.mapShowEntitiesToDomain(it)
            }
}