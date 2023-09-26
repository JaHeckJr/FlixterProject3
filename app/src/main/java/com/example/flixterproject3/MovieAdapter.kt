package com.example.flixterproject3

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MovieAdapter(private val context: Context, private val movies: List<Movie>) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.movieTitle)
        private val overview: TextView = itemView.findViewById(R.id.movieDescription)
        private val poster: ImageView = itemView.findViewById(R.id.moviePoster)

        fun bind(movie: Movie) {
            title.text = movie.title
            overview.text = movie.overview

            Glide.with(context)
                .load("https://image.tmdb.org/t/p/w500/${movie.posterPath}")
                .into(poster)

            itemView.setOnClickListener {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra("title", movie.title)
                intent.putExtra("overview", movie.overview)
                intent.putExtra("poster", movie.posterPath)
                intent.putExtra("rating", movie.rating)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}
