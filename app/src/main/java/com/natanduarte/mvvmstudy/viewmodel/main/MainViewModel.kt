package com.natanduarte.mvvmstudy.viewmodel.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.natanduarte.mvvmstudy.model.Activity
import com.natanduarte.mvvmstudy.repository.MainRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel constructor(
    private val repository: MainRepository
) : ViewModel() {

    val activity = MutableLiveData<Activity>()
    val errorMessage = MutableLiveData<String>()

    fun getActivity() {
        val request = repository.getActivity()

        request.enqueue(object : Callback<Activity> {
            override fun onResponse(
                call: Call<Activity>,
                response: Response<Activity>
            ) {
                activity.postValue(response.body())
            }

            override fun onFailure(call: Call<Activity>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}
