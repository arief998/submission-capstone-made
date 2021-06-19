package com.example.moviecatalogue.ui.movie

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import org.koin.android.viewmodel.ext.android.viewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.data.Resource
import com.example.moviecatalogue.databinding.FragmentMovieBinding

class MovieFragment : Fragment() {
    private lateinit var binding : FragmentMovieBinding
    private lateinit var mApiAdapter: MovieApiAdapter
    private val viewModel: MovieViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(activity != null) {
            mApiAdapter = MovieApiAdapter()

            viewModel.getMovieApi().observe(viewLifecycleOwner, { _movies ->
                if(_movies != null){
                    when(_movies){
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            mApiAdapter.setMovieApi(_movies.data)

                            with(binding.rvMovie){
                                layoutManager = LinearLayoutManager(context)
                                adapter = mApiAdapter
                            }
                        }
                        else -> return@observe
                    }
                }
            })
        }
    }
}