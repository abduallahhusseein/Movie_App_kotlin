package com.example.mymovie.ui.single_movie_details

import androidx.lifecycle.LiveData
import com.example.mymovie.data.api.TheMovieDBInterface
import com.example.mymovie.data.repository.MovieDetailsNetworkDataSource
import com.example.mymovie.data.repository.NetworkState
import com.example.mymovie.data.valueObject.MovieDetails
import io.reactivex.disposables.CompositeDisposable

class MovieDetailsRepository(private val apiService: TheMovieDBInterface) {

    lateinit var movieDetailsNetworkStateDataSource: MovieDetailsNetworkDataSource

    fun fetchSinglemovieDetails (compositeDisposable: CompositeDisposable,movieId: Int):LiveData<MovieDetails>{

        movieDetailsNetworkStateDataSource = MovieDetailsNetworkDataSource(apiService, compositeDisposable)
        movieDetailsNetworkStateDataSource.fetchMovieDetails(movieId)


        return movieDetailsNetworkStateDataSource.downloadedMovieDetailsResponse
    }

    fun getMovieDetailsNetworkState() : LiveData<NetworkState>{

        return movieDetailsNetworkStateDataSource.networkState
    }
}