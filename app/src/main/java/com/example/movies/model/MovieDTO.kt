package com.example.movies.model

import java.io.Serializable

data class MovieDTO(

    val title: String,
    val posterPath: String,
    val overview: String,
    val voteAverage: String,
    val releaseDate: String


) : Serializable {

    fun fullImagePath() = "https://image.tmdb.org/t/p/w500$posterPath"
}