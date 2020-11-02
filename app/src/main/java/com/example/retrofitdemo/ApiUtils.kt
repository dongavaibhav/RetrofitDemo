package com.example.retrofitdemo

object ApiUtils {
    val BASE_URL = "http://dummy.restapiexample.com/api/v1/"

    val apiService: ApiInterface
        get() = RetrofitClient.getClient(BASE_URL)!!.create(ApiInterface::class.java)
}