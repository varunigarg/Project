package com.example.movies.data.repository.impl

import com.example.movies.data.ApiClient
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("top_rated?api_key=9f5c43b3200b5e7b560d72d40f3a4e0f")
    fun callMoviesAPI(): Call<JsonObject>
}


object ApiUtils {
    val BASE_URL = "https://api.themoviedb.org/3/movie/"
    val apiService: ApiInterface
        get() = ApiClient.getClient(BASE_URL)!!.create(ApiInterface::class.java)
}

