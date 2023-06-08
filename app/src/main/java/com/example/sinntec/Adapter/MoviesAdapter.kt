package com.example.sinntec.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.sinntec.API.MovieItem
import com.example.sinntec.Fragment.MoviesDirections
import com.example.sinntec.databinding.MovieElementBinding
import com.squareup.picasso.Picasso

class MoviesAdapter (private var courseList: ArrayList<MovieItem>): RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return courseList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(courseList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(MovieElementBinding.inflate(LayoutInflater.from(parent.context)))
    }

    inner class ViewHolder(private val binding: MovieElementBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(pelicula: MovieItem){
            Picasso.get().load("https://image.tmdb.org/t/p/w500/${pelicula.poster_path}").into(binding.imagenPoster)
            binding.tituloMovie.text = pelicula.title
            binding.ratingBar.rating = (pelicula.vote_average.toFloat()/2)
            binding.calificacion.text = "Rating: ${pelicula.vote_average}"

            binding.relative.setOnClickListener {
                val action = MoviesDirections.actionMoviesToSpecificMovie(pelicula.overview,pelicula.backdrop_path,pelicula.title,pelicula.vote_average.toFloat())
                it.findNavController().navigate(action)
            }
        }
    }
}