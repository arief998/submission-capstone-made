package com.example.favorite.show

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.favorite.FavoriteViewModel
import com.example.moviecatalogue.favorite.databinding.FragmentShowFavoriteBinding
import com.example.favorite.di.favoriteModule
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules


class ShowFavoriteFragment : Fragment() {
    private lateinit var binding: FragmentShowFavoriteBinding
    private lateinit var mAdapter: ShowAdapter
    private val viewModel: FavoriteViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShowFavoriteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadKoinModules(favoriteModule)

        if(activity != null){
            mAdapter = ShowAdapter()

            viewModel.getListShowFavorite().observe(viewLifecycleOwner, { list ->
                mAdapter.setTvShow(list)
                with(binding.rvTvshow){
                    layoutManager = LinearLayoutManager(view.context)
                    setHasFixedSize(true)
                    adapter = mAdapter
                }
            })

        }
    }
}