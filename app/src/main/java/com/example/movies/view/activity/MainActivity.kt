package com.example.movies.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movies.R
import com.example.movies.databinding.ActivityMainBinding
import com.example.movies.view.viewmodel.MainViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.hide()
        initBindings()
        initViewModel()
        binding.gridView.layoutManager = GridLayoutManager(this, 2)
        viewModel.apiCall()
    }

    private fun initBindings() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    private fun initViewModel() {
        viewModel =
            ViewModelProvider(this).get(MainViewModel::class.java)
        binding.viewModel = viewModel
    }


}
