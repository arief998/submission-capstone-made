package com.example.favorite.show

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core.domain.model.TvShow
import com.example.moviecatalogue.databinding.TvshowItemBinding
import com.example.favorite.detail.ShowFavoriteDetailActivity
import com.example.favorite.detail.ShowFavoriteDetailActivity.Companion.EXTRA_SHOW
import com.example.moviecatalogue.ui.movie.MovieApiAdapter.Companion.URL

class ShowAdapter: RecyclerView.Adapter<ShowAdapter.ShowViewHolder>() {
    private var list  = ArrayList<TvShow>()

    fun setTvShow (movie: List<TvShow>?){
        if(movie == null) return
        list.clear()
        list.addAll(movie)
    }


    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): ShowViewHolder {
        val binding = TvshowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShowViewHolder(binding)

    }
    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ShowViewHolder, position: Int) {
        holder.bind(list[position])

        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, ShowFavoriteDetailActivity::class.java)
            intent.putExtra(EXTRA_SHOW, list[holder.adapterPosition])
            it.context.startActivity(intent)
        }
    }

    class ShowViewHolder(private val binding: TvshowItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: TvShow){
            binding.let{
                it.tvTitle.text = movie.originalName
                it.tvGenre.text = movie.voteAverage.toString()
                it.tvDuration.text = movie.firstAirDate
                Glide.with(it.root)
                        .load(URL+movie.posterPath)
                        .centerCrop()
                        .into(it.imgPoster)
            }
        }
    }


}