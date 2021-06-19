package com.example.favorite.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core.domain.model.Movie
import com.example.moviecatalogue.databinding.MoviesItemApiBinding
import com.example.favorite.detail.MovieFavoriteDetailActivity
import com.example.favorite.detail.MovieFavoriteDetailActivity.Companion.EXTRA_MOVIE
import com.example.moviecatalogue.ui.movie.MovieApiAdapter

class MovieFavoriteAdapter: RecyclerView.Adapter<MovieFavoriteAdapter.MovieViewHolder>() {
    private val list = ArrayList<Movie>()

    fun setMovie(movie: List<Movie>?){
        if(movie == null) return
        list.clear()
        list.addAll(movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = MoviesItemApiBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(list[position])

        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, MovieFavoriteDetailActivity::class.java)
            intent.putExtra(EXTRA_MOVIE, list[holder.adapterPosition])
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class MovieViewHolder(private val binding: MoviesItemApiBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(movie: Movie){
            binding.let{
                it.tvTitle.text = movie.originalTitle
                it.tvGenre.text = movie.voteAverage.toString()
                it.tvDuration.text = movie.releaseDate
                Glide.with(it.root)
                        .load(MovieApiAdapter.URL +movie.posterPath)
                        .centerCrop()
                        .into(it.imgPoster)
            }
        }
    }
}