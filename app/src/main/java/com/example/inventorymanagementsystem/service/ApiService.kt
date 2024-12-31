package com.example.inventorymanagementsystem.service

import com.example.inventorymanagementsystem.model.Item
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("27f169a7-a022-4894-a66a-9d779f588882")
    fun getItems(): Call<List<Item>>

}
