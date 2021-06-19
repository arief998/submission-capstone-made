package com.example.favorite.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.core.domain.model.TvShow
import com.example.moviecatalogue.R
import com.example.favorite.FavoriteViewModel
import com.example.moviecatalogue.favorite.databinding.ActivityShowFavoriteDetailBinding
import com.example.favorite.show.ShowAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class ShowFavoriteDetailActivity : AppCompatActivity() {
    companion object{
        const val EXTRA_SHOW = "extra_show"
    }

    private lateinit var binding: ActivityShowFavoriteDetailBinding
    private lateinit var show : TvShow
    private val showViewModel: FavoriteViewModel by viewModel()
    private var isFavorite = false
    private lateinit var showAdapter : ShowAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowFavoriteDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent.extras
        showAdapter = ShowAdapter()

        if(intent != null){
            show = intent.getParcelable<TvShow>(EXTRA_SHOW) as TvShow
            initView(show)
        }

        showViewModel.getShowById(show.id).observe(this, { listShow ->
            checkState(listShow[0].favorite)
            isFavorite = checkState(listShow[0].favorite)
            if(isFavorite){
                binding.btnFavoriteShow.setOnClickListener {
                    removeShowFromFavorite(listShow[0])
                }
            } else {
                binding.btnFavoriteShow.setOnClickListener {
                    addShowToFavorite(listShow[0])
                }
            }
        })

    }

    private fun addShowToFavorite(show: TvShow) {
        show.favorite = true
        showViewModel.addShowToFavorite(show)
    }

    private fun removeShowFromFavorite(show: TvShow) {
        show.favorite = false
        showViewModel.addShowToFavorite(show)
    }

    private fun initView(show: TvShow) {
        with(binding){
            tvTitleDetail.text = show.originalName
            tvSynopsisDetail.text = show.overview
            tvRate.text = show.voteAverage.toString()
            tvDate.text = show.firstAirDate
            Glide.with(binding.root)
                    .load("https://image.tmdb.org/t/p/w500/" + show.posterPath)
                    .into(imgPosterDetail)
        }
    }

    private fun checkState(state: Boolean): Boolean{
        return if(state){
            binding.btnFavoriteShow.background = ContextCompat.getDrawable(this, R.drawable.ic_favorite_red)
            true
        }else {
            binding.btnFavoriteShow.background = ContextCompat.getDrawable(this, R.drawable.ic_favorite_white)
            false
        }
    }
}