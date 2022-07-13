package com.example.innoctive_test.view.activity

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.innoctive_test.Utils.Constants
import com.example.innoctive_test.databinding.ActivityMainBinding
import com.example.innoctive_test.service.MainRepository
import com.example.innoctive_test.service.RetrofitService
import com.example.innoctive_test.view.adapter.ImageAdapter
import com.example.innoctive_test.view.onPageListener
import com.example.innoctive_test.viewmodel.MainViewModel
import com.example.innoctive_test.viewmodel.MyViewModelFactory

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding
    private lateinit var activity: Activity
    lateinit var viewModel: MainViewModel
    private val retrofitService = RetrofitService.getInstance()
    private lateinit var adapter: ImageAdapter
    var page = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        activity = this

        viewModel = ViewModelProvider(
            this,
            MyViewModelFactory(MainRepository(retrofitService))
        )[MainViewModel::class.java]
        binding.recImage.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        adapter = ImageAdapter(object : onPageListener {
            override fun onPage() {
                page += 1
                loadImages()
            }

        })
        binding.recImage.adapter = adapter
        viewModel.movieList.observe(this) {
            Log.d(TAG, "onCreate: $it")
            adapter.setImageList(it)
        }
        viewModel.errorMessage.observe(this) {
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()

        }
        loadImages()
        setListeners()
    }

    private fun loadImages() {
        viewModel.getAllImages(Constants.LIMIT, page, Constants.ORDER)
    }

    private fun setListeners() {
        binding.swipe.setOnRefreshListener {
            binding.swipe.isRefreshing = false
            adapter.clearData()
            loadImages()

        }

    }
}