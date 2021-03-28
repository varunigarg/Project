package com.example.movies.view.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies.R
import com.example.movies.databinding.RowMovieBinding
import com.example.movies.model.MovieDTO
import com.example.movies.view.activity.DetailActivity

class Adapter(
) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    private lateinit var context: Context
    private var list: List<MovieDTO> = arrayListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: RowMovieBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.row_movie, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.movie = list[position]

        holder.binding.clMovie.setOnClickListener {
            val i = Intent(context, DetailActivity::class.java)
            i.putExtra("movie_data", list[position])
            (context as Activity).startActivity(i)
        }

        Glide.with(context)
            .load(list[position].fullImagePath())
            .into(holder.binding.ivMovieIcon)

    }

    fun updateList(updatedList: List<MovieDTO>) {
        list = updatedList
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: RowMovieBinding) :
        RecyclerView.ViewHolder(binding.root)
}
