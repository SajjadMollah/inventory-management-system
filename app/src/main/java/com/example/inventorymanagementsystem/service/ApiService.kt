package com.example.inventorymanagementsystem.service

import com.example.inventorymanagementsystem.model.Item
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/")
    fun getItems(): Call<List<Item>>

}
