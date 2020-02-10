package com.example.mymovie.data.valueObject


import com.google.gson.annotations.SerializedName

data class MovieResponse(
    val page: Int,
    val movieList: List<Movie>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)