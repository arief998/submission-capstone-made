package com.example.moviecatalogue.ui.tvshow

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core.domain.model.TvShow
import com.example.moviecatalogue.databinding.TvshowItemBinding
import com.example.moviecatalogue.ui.detail.DetailActivity
import com.example.moviecatalogue.ui.detail.DetailActivity.Companion.EXTRA_ID
import com.example.moviecatalogue.ui.detail.DetailActivity.Companion.EXTRA_TV
import com.example.moviecatalogue.ui.detail.DetailActivity.Companion.TV_ID
import com.example.moviecatalogue.ui.movie.MovieApiAdapter.Companion.URL

class TvShowAdapter: RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {
    private var list  = ArrayList<TvShow>()

    fun setTvShow (movie: List<TvShow>?){
        if(movie == null) return
        list.clear()
        list.addAll(movie)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): TvShowViewHolder {
        val binding = TvshowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(binding)

    }
    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        holder.bind(list[position])

        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, DetailActivity::class.java)
            intent.putExtra(EXTRA_TV, list[holder.adapterPosition])
            intent.putExtra(EXTRA_ID, TV_ID)
            it.context.startActivity(intent)
        }
    }

    class TvShowViewHolder(private val binding: TvshowItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: TvShow){
            with(binding){
                tvTitle.text = movie.originalName
                tvGenre.text = movie.voteAverage.toString()
                tvDuration.text = movie.firstAirDate
                Glide.with(binding.root)
                        .load(URL+movie.posterPath)
                        .centerCrop()
                        .into(binding.imgPoster)
            }
        }
    }


}