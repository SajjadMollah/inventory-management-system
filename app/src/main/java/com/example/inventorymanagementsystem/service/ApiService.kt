package com.example.inventorymanagementsystem.service

import com.example.inventorymanagementsystem.model.Item
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("f5086234-4e69-48f9-a736-f7dc1a3b3996")
    fun getItems(): Call<List<Item>>

}
