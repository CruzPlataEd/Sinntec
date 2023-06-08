package com.example.sinntec.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sinntec.API.MovieAPI
import com.example.sinntec.API.MovieItem
import com.example.sinntec.API.Repository
import com.example.sinntec.Adapter.MoviesAdapter
import com.example.sinntec.R
import com.example.sinntec.databinding.FragmentMoviesBinding
import kotlinx.coroutines.launch

class Movies : Fragment() {
    private var _binding: FragmentMoviesBinding?= null
    private val binding get() = checkNotNull(_binding){ "Cannot access binding because it is null. Is the view visible?" }
    lateinit var adapter: MoviesAdapter
    private val moviesApi = Repository.getInstance().create(MovieAPI::class.java)
    lateinit var peliculasLista: ArrayList<MovieItem>
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentMoviesBinding.inflate(inflater,container,false)
        binding.topRecycler.setHasFixedSize(true)
        binding.topRecycler.layoutManager = LinearLayoutManager(requireContext())
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val resultadoTop = moviesApi.fetchMovie("top_rated?api_key=c88e52755192d1c1990a4199bf3f4ffa&language=en-US&page=${1}")
                adapter = MoviesAdapter(resultadoTop.results)
                binding.topRecycler.adapter = adapter
            }catch(e:Exception){}
        }

        return binding.root
    }

}