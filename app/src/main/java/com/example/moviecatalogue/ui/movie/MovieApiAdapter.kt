package com.example.moviecatalogue.ui.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core.domain.model.Movie
import com.example.moviecatalogue.databinding.MoviesItemApiBinding
import com.example.moviecatalogue.ui.detail.DetailActivity
import com.example.moviecatalogue.ui.detail.DetailActivity.Companion.EXTRA_API_MOVIE
import com.example.moviecatalogue.ui.detail.DetailActivity.Companion.EXTRA_ID
import com.example.moviecatalogue.ui.detail.DetailActivity.Companion.MOVIE_ID

class MovieApiAdapter: RecyclerView.Adapter<MovieApiAdapter.MovieApiViewHolder>() {
    private var mList = ArrayList<Movie>()
    companion object{
        const val URL = "https://image.tmdb.org/t/p/w500/"
    }

    fun setMovieApi(movie: List<Movie>?){
        if(movie == null) return
        mList.clear()
        mList.addAll(movie)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieApiAdapter.MovieApiViewHolder {
        val binding = MoviesItemApiBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieApiViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieApiAdapter.MovieApiViewHolder, position: Int) {
        holder.bind(mList[position])
        
        holder.itemView.setOnClickListener { 
            val intent = Intent(it.context, DetailActivity::class.java)
            intent.putExtra(EXTRA_API_MOVIE, mList[holder.adapterPosition])
            intent.putExtra(EXTRA_ID, MOVIE_ID)
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    inner class MovieApiViewHolder(private val binding: MoviesItemApiBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(movie: Movie){
            with(binding){
                tvTitle.text = movie.originalTitle
                tvGenre.text = movie.voteAverage.toString()
                tvDuration.text = movie.releaseDate
                Glide.with(binding.root)
                    .load(URL+movie.posterPath)
                    .centerCrop()
                    .into(binding.imgPoster)
            }
        }
    }
}