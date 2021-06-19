package com.example.moviecatalogue.ui.tvshow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.data.Resource
import com.example.moviecatalogue.databinding.FragmentTvShowBinding
import org.koin.android.viewmodel.ext.android.viewModel

class TvShowFragment : Fragment() {
    private lateinit var binding: FragmentTvShowBinding
    private lateinit var mAdapter : TvShowAdapter
    private val viewModel: TvShowViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentTvShowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAdapter = TvShowAdapter()

        if(activity != null){
            viewModel.getTvShowApi().observe(viewLifecycleOwner, { tvShow ->
                if(tvShow != null){
                    when(tvShow){
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            mAdapter.setTvShow(tvShow.data)

                            with(binding.rvTvshow){
                                layoutManager = LinearLayoutManager(context)
                                adapter = mAdapter
                            }
                        }
                        else -> return@observe
                    }
                }
            })

        }
    }

}