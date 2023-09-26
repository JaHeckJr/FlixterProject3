package com.example.flixterproject3

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val intent = intent
        val title = intent.getStringExtra("title")
        val overview = intent.getStringExtra("overview")
        val poster = intent.getStringExtra("poster")
        val rating = intent.getDoubleExtra("rating", 0.0)

        val movieTitle = findViewById<TextView>(R.id.movieTitle)
        val movieOverview = findViewById<TextView>(R.id.movieOverview)
        val movieRating = findViewById<TextView>(R.id.movieRating)
        val posterImage = findViewById<ImageView>(R.id.posterImage)

        movieTitle.text = title
        movieOverview.text = overview
        movieRating.text = rating.toString()

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500/$poster")
            .into(posterImage)
    }
}
