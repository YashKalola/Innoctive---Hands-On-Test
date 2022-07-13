package com.example.innoctive_test.service

import com.example.innoctive_test.Utils.Constants
import com.example.innoctive_test.viewmodel.model.DefaultModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("search")
    fun getImages(@Query("limit")limit: Int,@Query("page") page: Int, @Query("order")order: String): Call<List<DefaultModel>>

    companion object {
        var retrofitService: RetrofitService? = null

        fun getInstance() : RetrofitService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }
}