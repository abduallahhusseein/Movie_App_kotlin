package com.example.mymovie.ui.popular_movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.mymovie.data.api.POST_PER_PAGE
import com.example.mymovie.data.api.TheMovieDBInterface
import com.example.mymovie.data.repository.MovieDataSource
import com.example.mymovie.data.repository.MovieDataSourceFactory
import com.example.mymovie.data.repository.NetworkState
import com.example.mymovie.data.valueObject.Movie
import io.reactivex.disposables.CompositeDisposable

class MoviePagedListRepository(private val apiservice : TheMovieDBInterface ) {

    lateinit var moviePagedList : LiveData<PagedList<Movie>>
    lateinit var movesDataSourceFactory: MovieDataSourceFactory

    fun fetchLiveMoviePagedList ( compositeDisposable: CompositeDisposable) : LiveData<PagedList<Movie>>{

        movesDataSourceFactory = MovieDataSourceFactory(apiservice , compositeDisposable)

        val config : PagedList.Config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(POST_PER_PAGE)
            .build()

        moviePagedList = LivePagedListBuilder(movesDataSourceFactory,config).build()

        return moviePagedList

    }
    fun getNetworkState() : LiveData<NetworkState>{

        return Transformations.switchMap<MovieDataSource, NetworkState>(
            movesDataSourceFactory.movieLiveDataSource, MovieDataSource::networkState)
    }
}