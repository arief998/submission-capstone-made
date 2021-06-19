package com.example.core.data.source.remote


import com.example.core.BuildConfig
import com.example.core.data.source.remote.response.ListResponse
import com.example.core.data.source.remote.response.MovieResponse
import com.example.core.data.source.remote.response.TvShowResponse
import retrofit2.http.GET

interface ApiService {
    @GET("movie/popular?api_key=${BuildConfig.MY_TMDB_API_KEY}")
    suspend fun getMovie(): ListResponse<MovieResponse>

    @GET("tv/popular?api_key=${BuildConfig.MY_TMDB_API_KEY}")
    suspend fun getTvShow(): ListResponse<TvShowResponse>
}