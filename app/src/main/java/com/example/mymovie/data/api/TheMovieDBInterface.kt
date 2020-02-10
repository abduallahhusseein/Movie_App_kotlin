package com.example.mymovie.data.api

import com.example.mymovie.data.valueObject.MovieDetails
import com.example.mymovie.data.valueObject.MovieResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieDBInterface {

    //https://api.themoviedb.org/3/movie/popular?api_key=83a3250794f3258e62cae1d7f5cca379&page=1(to get popular movie)
    //https://api.themoviedb.org/3/movie/181812?api_key=83a3250794f3258e62cae1d7f5cca379(to get movie details)
    //https://api.themoviedb.org/3/(base url of retrofit)

    //how to use retrofit
    @GET("movie/popular")
    fun getPopularMovie(@Query("page")page : Int) : Single<MovieResponse>

    @GET("movie/{movie_id}")//movie/181812 which it after the url of retrofit
    fun getMovieDetails(@Path("movie_id")id: Int): Single<MovieDetails>
}