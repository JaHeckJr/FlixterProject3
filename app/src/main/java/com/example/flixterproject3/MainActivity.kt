package com.example.flixterproject3
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var requestQueue: RequestQueue
    private lateinit var movieList: MutableList<Movie>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        requestQueue = Volley.newRequestQueue(this)
        movieList = ArrayList()

        fetchMovies()
    }

    private fun fetchMovies() {
        val apiKey = "a07e22bc18f5cb106bfe4cc1f83ad8ed"
        val url = "https://api.themoviedb.org/3/movie/now_playing?api_key=$apiKey"

        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                for (i in 0 until response.length()) {
                    try {
                        val jsonObject = response.getJSONObject(i)
                        val title = jsonObject.getString("title")
                        val overview = jsonObject.getString("overview")
                        val poster = jsonObject.getString("poster_path")
                        val rating = jsonObject.getDouble("vote_average")

                        val movie = Movie(title, poster, overview, rating)
                        movieList.add(movie)
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
                val adapter = MovieAdapter(this, movieList)
                recyclerView.adapter = adapter
            },
            Response.ErrorListener { error ->
                Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
            }
        )

        requestQueue.add(jsonArrayRequest)
    }
}
