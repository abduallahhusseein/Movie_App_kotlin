package com.example.mymovie.data.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.mymovie.data.api.TheMovieDBInterface
import com.example.mymovie.data.valueObject.Movie
import io.reactivex.disposables.CompositeDisposable

class MovieDataSourceFactory(private val apiService: TheMovieDBInterface, private val compositeDisposable: CompositeDisposable)
    : DataSource.Factory<Int, Movie>() {

    val movieLiveDataSource = MutableLiveData<MovieDataSource>()

    override fun create(): DataSource<Int, Movie> {

        val movieDataSource = MovieDataSource(apiService,compositeDisposable)
        movieLiveDataSource.postValue(movieDataSource)

        return movieDataSource
    }
}