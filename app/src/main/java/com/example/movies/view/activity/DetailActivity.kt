package com.example.movies.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.movies.R
import com.example.movies.model.MovieDTO
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    lateinit var movieDTO: MovieDTO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar!!.hide()
        val intent = intent!!
        movieDTO = intent.getSerializableExtra("movie_data") as MovieDTO
        initViews()
    }

    private fun initViews() {
        Glide.with(this)
            .load(movieDTO.fullImagePath())
            .into(ivMovie)
        tvMoiveName.text = movieDTO.title
        tvRating.text = movieDTO.voteAverage
        tvOdes.text = movieDTO.overview
//        tvMoiveName.text = movieDTO.title
    }
}
