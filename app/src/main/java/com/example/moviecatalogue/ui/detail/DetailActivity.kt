package com.example.moviecatalogue.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.example.core.domain.model.Movie
import com.example.core.domain.model.TvShow
import com.example.moviecatalogue.R
import com.example.moviecatalogue.databinding.ActivityDetailBinding
import com.example.moviecatalogue.ui.movie.MovieApiAdapter.Companion.URL
import com.example.moviecatalogue.ui.movie.MovieViewModel
import com.example.moviecatalogue.ui.tvshow.TvShowViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {
    companion object{
        const val EXTRA_TV = "extra_tv"
        const val EXTRA_API_MOVIE = "extra_api_movie"

        const val EXTRA_ID = "extra_id"

        const val MOVIE_ID = 1
        const val TV_ID = 0
    }

    private lateinit var binding: ActivityDetailBinding
    private val movieViewModel: MovieViewModel by viewModel()
    private lateinit var movieFavorite: Movie
    private lateinit var showFavorite: TvShow
    private val showViewModel: TvShowViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent.extras

        if(intent != null){
            val id = intent.getInt(EXTRA_ID, 0)
            if(id == 0){
                showFavorite = intent.getParcelable<TvShow>(EXTRA_TV) as TvShow

                showViewModel.getShowById(showFavorite.id).observe(this, { show ->
                    initViewTv(show[0])
                    checkState(show[0].favorite)

                    binding.btnFavoriteShow.setOnClickListener {
                        if(show[0].favorite){
                            removeShowFromFavorite(show[0])
                        } else {
                            addShowToFavorite(show[0])
                        }
                    }
                })

            } else {
                movieFavorite = intent.getParcelable<Movie>(EXTRA_API_MOVIE) as Movie

                movieViewModel.getMovieById(movieFavorite.id).observe(this, { movie ->
                    initViewMovie(movie[0])
                    checkState(movie[0].favorite)

                    binding.btnFavoriteMovie.setOnClickListener {
                        if(movie[0].favorite){
                            removeFromFavorite(movie[0])
                        } else {
                            addToFavorite(movie[0])
                        }
                    }
                })
            }

        }
        supportActionBar?.title = movieFavorite.originalTitle


    }

    private fun initViewTv(tv: TvShow){
        with(binding){
            tvTitleDetail.text = tv.originalName
            tvSynopsisDetail.text = tv.overview
            tvRate.text = tv.voteAverage.toString()
            tvDate.text = tv.firstAirDate
            Glide.with(binding.root)
                .load(URL + tv.posterPath)
                .into(imgPosterDetail)
            btnFavoriteShow.visibility = View.VISIBLE
        }
    }

    private fun initViewMovie(movie: Movie){
        with(binding){
            tvTitleDetail.text = movie.originalTitle
            tvSynopsisDetail.text = movie.overview
            tvRate.text = movie.voteAverage.toString()
            tvDate.text = movie.releaseDate
            Glide.with(binding.root)
                .load(URL + movie.posterPath)
                .override(300, 400)
                .into(imgPosterDetail)
            btnFavoriteMovie.visibility = View.VISIBLE

        }
    }

    private fun addToFavorite(movie: Movie) {
        movie.favorite = true
        movieViewModel.addToFavorite(movie)
    }

    private fun addShowToFavorite(show: TvShow){
        show.favorite = true
        showViewModel.addToFavorite(show)
    }

    private fun removeFromFavorite(movie: Movie){
        movie.favorite = false
        movieViewModel.addToFavorite(movie)
    }

    private fun checkState(state: Boolean) {
        return if(state){
            if(binding.btnFavoriteMovie.isVisible){
                binding.btnFavoriteMovie.background = ContextCompat.getDrawable(this, R.drawable.ic_favorite_red)
            }else {
                binding.btnFavoriteShow.background = ContextCompat.getDrawable(this, R.drawable.ic_favorite_red)
            }
        }else {
            if(binding.btnFavoriteMovie.isVisible){
                binding.btnFavoriteMovie.background = ContextCompat.getDrawable(this, R.drawable.ic_favorite_white)
            } else {
                binding.btnFavoriteShow.background = ContextCompat.getDrawable(this, R.drawable.ic_favorite_white)
            }

        }
    }

    private fun removeShowFromFavorite(showFavorite: TvShow){
        showFavorite.favorite = false
        showViewModel.addToFavorite(showFavorite)
    }
}