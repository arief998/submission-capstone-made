package com.example.favorite.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.favorite.FavoriteViewModel
import com.example.moviecatalogue.favorite.databinding.FragmentMovieFavoriteBinding
import com.example.favorite.di.favoriteModule
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class MovieFavoriteFragment : Fragment() {
    private lateinit var binding: FragmentMovieFavoriteBinding
    private val viewModel: FavoriteViewModel by viewModel()
    private lateinit var mAdapter: MovieFavoriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAdapter = MovieFavoriteAdapter()
        loadKoinModules(favoriteModule)

        viewModel.getListMovieFavorite().observe(viewLifecycleOwner, { listMovie ->
            mAdapter.setMovie(listMovie)

            with(binding.rvMovies){
                layoutManager = LinearLayoutManager(view.context)
                setHasFixedSize(true)
                adapter = mAdapter

            }
        })

    }
}