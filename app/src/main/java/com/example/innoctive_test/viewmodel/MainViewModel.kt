package com.example.innoctive_test.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.innoctive_test.service.MainRepository
import com.example.innoctive_test.viewmodel.model.DefaultModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel constructor(private val repository: MainRepository)  : ViewModel() {

    val movieList = MutableLiveData<List<DefaultModel>>()
    val errorMessage = MutableLiveData<String>()

    fun getAllImages(limit: Int, page: Int, order: String) {
        val response = repository.getImages(limit, page, order)
        response.enqueue(object : Callback<List<DefaultModel>> {
            override fun onResponse(call: Call<List<DefaultModel>>, response: Response<List<DefaultModel>>) {
                movieList.value = response.body()
            }
            override fun onFailure(call: Call<List<DefaultModel>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}
