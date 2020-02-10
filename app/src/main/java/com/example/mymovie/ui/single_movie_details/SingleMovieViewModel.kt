package com.example.mymovie.ui.single_movie_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mymovie.data.repository.NetworkState
import com.example.mymovie.data.valueObject.MovieDetails
import io.reactivex.disposables.CompositeDisposable

class SingleMovieViewModel (private val movieRepository: MovieDetailsRepository, movieId : Int) : ViewModel(){

    private val compositeDisposable =CompositeDisposable()

            val movieDetails : LiveData<MovieDetails> by lazy{
                movieRepository.fetchSinglemovieDetails(compositeDisposable,movieId)
            }

            val networkState : LiveData<NetworkState> by lazy {
                movieRepository.getMovieDetailsNetworkState()
            }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}