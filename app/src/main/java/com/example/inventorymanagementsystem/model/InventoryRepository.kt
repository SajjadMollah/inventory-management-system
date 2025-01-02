package com.example.inventorymanagementsystem.model

import com.example.inventorymanagementsystem.clients.ApiClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class InventoryRepository {
    private val _inventory = MutableStateFlow<List<Item>>(listOf())
    val inventory = _inventory.asStateFlow()

    suspend fun fetchItems(): Result<List<Item>> = suspendCoroutine { continuation ->
        try {
            ApiClient.apiService.getItems().enqueue(object : Callback<List<Item>> {
                override fun onResponse(call: Call<List<Item>>, response: Response<List<Item>>) {
                    if (response.isSuccessful) {
                        val items = response.body() ?: emptyList()
                        _inventory.update { items }
                        continuation.resume(Result.success(items))
                    } else {
                        continuation.resume(Result.failure(Exception("Failed to fetch items: ${response.code()}")))
                    }
                }

                override fun onFailure(call: Call<List<Item>>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        } catch (e: Exception) {
            continuation.resumeWithException(e)
        }
    }

    fun addItem(item: Item) {
        _inventory.update { currentList -> currentList + item }
    }

    fun removeItem(item: Item) {
        _inventory.update { currentList -> currentList - item }
    }
}