package com.example.retrofitdemo

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiInterface {

    @POST("/create")
    @FormUrlEncoded
    fun registrationPost(
        @Field("name") name: String?,
        @Field("salary") salary: String?,
        @Field("age") age: String
    ): Call<DataModel?>?
}