package com.natanduarte.mvvmstudy.repository

import com.natanduarte.mvvmstudy.rest.RetrofitService

class MainRepository constructor(
    private val retrofitService: RetrofitService
) {

    fun getActivity() = retrofitService.getActivity()
}
