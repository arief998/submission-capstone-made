package com.example.favorite.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.core.domain.model.Movie
import com.example.moviecatalogue.R
import com.example.favorite.FavoriteViewModel
import com.example.moviecatalogue.favorite.databinding.ActivityMovieFavoriteDetailBinding
import org.koin.android.viewmodel.ext.android.viewModel

class MovieFavoriteDetailActivity : AppCompatActivity() {
    companion object{
        const val EXTRA_MOVIE = "extra_movie"
    }

    private lateinit var binding: ActivityMovieFavoriteDetailBinding
    private val viewModel: FavoriteViewModel by viewModel()
    private lateinit var movie : Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieFavoriteDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent.extras

        if(intent != null){
            movie = intent.getParcelable<Movie>(EXTRA_MOVIE) as Movie
            initView(movie)

        }

        viewModel.getMovieById(movie.id).observe(this, { movie ->
            checkState(movie[0].favorite)
            if(movie[0].favorite){
                binding.btnFavoriteMovie.setOnClickListener {
                    removeFromFavorite(movie[0])
                }
            } else {
                binding.btnFavoriteMovie.setOnClickListener {
                    addToFavorite(movie[0])
                }
            }
        })
    }

    private fun initView(movie: Movie) {
        with(binding){
            tvTitleDetail.text = movie.originalTitle
            tvSynopsisDetail.text = movie.overview
            tvRate.text = movie.voteAverage.toString()
            tvDate.text = movie.releaseDate
            com.bumptech.glide.Glide.with(binding.root)
                .load("https://image.tmdb.org/t/p/w500/" + movie.posterPath)
                .into(imgPosterDetail)
        }
    }

    private fun checkState(state: Boolean): Boolean{
        return if(state){
            binding.btnFavoriteMovie.background = ContextCompat.getDrawable(this, R.drawable.ic_favorite_red)
            true
        }else {
            binding.btnFavoriteMovie.background = ContextCompat.getDrawable(this, R.drawable.ic_favorite_white)
            false
        }
    }

    private fun addToFavorite(movie: Movie) {
        movie.favorite = true
        viewModel.addToFavorite(movie)
    }

    private fun removeFromFavorite(movie: Movie){
        movie.favorite = false
        viewModel.addToFavorite(movie)
    }
}