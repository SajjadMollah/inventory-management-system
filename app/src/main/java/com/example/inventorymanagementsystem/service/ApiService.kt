package com.example.inventorymanagementsystem.service

import com.example.inventorymanagementsystem.model.Item
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("8f2ed493-7e2c-4575-b2ab-a694243b209c")
    fun getItems(): Call<List<Item>>

}
