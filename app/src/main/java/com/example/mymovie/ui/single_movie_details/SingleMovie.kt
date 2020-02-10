package com.example.mymovie.ui.single_movie_details
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.mymovie.R
import com.example.mymovie.data.api.POSTER_BASE_URL
import com.example.mymovie.data.api.TheMovieDBClient
import com.example.mymovie.data.api.TheMovieDBInterface
import com.example.mymovie.data.repository.NetworkState
import com.example.mymovie.data.valueObject.MovieDetails
import kotlinx.android.synthetic.main.activity_single_movie.*
import java.text.NumberFormat
import java.util.*
class SingleMovie : AppCompatActivity() {

    private lateinit var viewModel: SingleMovieViewModel
    private lateinit var movieDetailsRepository: MovieDetailsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_movie)

        val movieId : Int = intent.getIntExtra("id",1)

        val apiService : TheMovieDBInterface = TheMovieDBClient.getClient()

        movieDetailsRepository = MovieDetailsRepository((apiService))

        viewModel = getViewModel(movieId)

        viewModel.movieDetails.observe(this, Observer {
            bindUI(it)
        })

        viewModel.networkState.observe(this, Observer {
            progress_bar.visibility = if(it == NetworkState.LOADING)View.VISIBLE else View.GONE
            txt_error.visibility = if(it == NetworkState.ERROR)View.VISIBLE else View.GONE
        })
    }

    private fun bindUI(it: MovieDetails) {
        movie_title.text = it.title
        movie_tagLine.text = it.tagline
        movie_release_date.text = it.releaseDate
        movie_rate.text = it.rating.toString()
        movie_runtime.text = it.runtime.toString() + "minutes"
        movie_overview.text = it.overview


        val formtCurrency = NumberFormat.getCurrencyInstance(Locale.US)
        movie_budget.text = formtCurrency.format(it.budget)
        movie_revenue.text = formtCurrency.format(it.revenue)

        val moviePosterURL = POSTER_BASE_URL + it.posterPath
        Glide.with(this)
            .load(moviePosterURL)
            .into(iv_movie_poster)
    }

    private fun getViewModel(movieId : Int): SingleMovieViewModel {

        return ViewModelProviders.of(this,object :ViewModelProvider.Factory{

            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @kotlin.Suppress("UNCHECKED_CAST") //To change body of created functions use File | Settings | File Templates.
                return SingleMovieViewModel(movieDetailsRepository,movieId) as T
            }
        })[SingleMovieViewModel::class.java]
    }
}
