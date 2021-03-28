package com.example.movies.data

import retrofit2.Response

interface WebServiceTask {
    fun onComplete(response: Response<*>?)
    fun onFailure(response: Any?)
}
