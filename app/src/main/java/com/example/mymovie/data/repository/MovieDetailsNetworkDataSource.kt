package com.example.mymovie.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mymovie.data.api.TheMovieDBInterface
import com.example.mymovie.data.valueObject.MovieDetails
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.lang.Exception

//to fetch data with rxjava

class MovieDetailsNetworkDataSource(private val apiService: TheMovieDBInterface,private  val compositeDisposable: CompositeDisposable){


    private val _networkState=MutableLiveData<NetworkState>()
            val networkState: LiveData<NetworkState>
    get() = _networkState  //similarly as using get method in java
    private val _downloadedMovieDetailsResponse = MutableLiveData<MovieDetails>()
            val downloadedMovieDetailsResponse : LiveData<MovieDetails>
    get()= _downloadedMovieDetailsResponse
    fun fetchMovieDetails(movieId : Int){

        _networkState.postValue(NetworkState.LOADING)

        try {
                compositeDisposable.add(
                    apiService.getMovieDetails(movieId)
                        .subscribeOn(Schedulers.io())
                        .subscribe(
                            {
                                _downloadedMovieDetailsResponse.postValue(it)
                                _networkState.postValue(NetworkState.LOADED)
                            }
                            ,
                            {
                                _networkState.postValue(NetworkState.ERROR)
                                Log.e("MovieDetailsDataSource",it.message)
                            }
                        )
                )
        }
        catch (e : Exception){
            Log.e("MovieDetailsDataSource",e.message)
        }
    }
}