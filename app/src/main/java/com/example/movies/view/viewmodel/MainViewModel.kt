package com.example.movies.view.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movies.App
import com.example.movies.data.repository.impl.ApiInterface
import com.example.movies.data.repository.impl.ApiUtils
import com.example.movies.model.MovieDTO
import com.example.movies.view.adapter.Adapter
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class MainViewModel : ViewModel() {

    val adapter = Adapter()
    var movieList = MutableLiveData<ArrayList<MovieDTO>>()
    var apiService: ApiInterface? = null

    init {
        movieList.value = arrayListOf()

    }

    fun apiCall() {
        movieList.value!!.clear()
        apiService = ApiUtils.apiService
        apiService!!.callMoviesAPI().enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                try {
                    if (response.body() != null) {
                        val jsonObject = response.body() as JsonObject?
                        Log.d("On Complete", "onComplete: $jsonObject")
                        if (jsonObject != null) {
                            var jsonArray = jsonObject.get("results") as JsonArray?
                            if (jsonArray != null) {
                                for (n in 0 until jsonArray.size()) {
                                    val jObject = jsonArray.get(n).asJsonObject
                                    val listDTO = MovieDTO(
                                        jObject.get("title").asString,
                                        jObject.get("poster_path").asString,
                                        jObject.get("overview").asString,
                                        jObject.get("vote_average").asString,
                                        jObject.get("release_date").asString
                                    )
                                    movieList.value!!.add(listDTO)
                                }
                            }
                            adapter.updateList(movieList.value!!)
                        }
                    } else {
                        Toast.makeText(App.getAppContext(), "null response", Toast.LENGTH_SHORT)
                            .show()
                    }
                } catch (e: Exception) {
                    Log.e("on complete error", "onComplete: ", e)
                    movieList.value?.clear()
                    adapter.updateList(movieList.value!!)
                }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Toast.makeText(App.getAppContext(), "failure", Toast.LENGTH_SHORT).show()
            }

        }
        )
    }

}