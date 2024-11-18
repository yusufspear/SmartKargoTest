package com.example.testapplication.apiService

import com.example.testapplication.model.ProductListModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("products")
    fun GetProductList() : Call<ArrayList<ProductListModel>>
}