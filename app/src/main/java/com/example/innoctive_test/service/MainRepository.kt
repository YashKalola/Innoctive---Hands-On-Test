package com.example.innoctive_test.service

class MainRepository constructor(private val retrofitService: RetrofitService) {
    fun getImages(limit: Int, page: Int, order: String) = retrofitService.getImages(limit,page,order)
}