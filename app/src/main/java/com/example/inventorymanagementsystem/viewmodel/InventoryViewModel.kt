package com.example.inventorymanagementsystem.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inventorymanagementsystem.clients.ApiClient
import com.example.inventorymanagementsystem.model.InventoryRepository
import com.example.inventorymanagementsystem.model.Item
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InventoryViewModel: ViewModel() {

    private val _inventory = MutableStateFlow<List<Item>>(listOf())

    val inventory: StateFlow<List<Item>> = _inventory.asStateFlow()


    init{
        addOnlineItems()
    }

    fun addItem(item: Item){
        _inventory.update { list -> list + item }
    }
    fun removeItem(item: Item){
        _inventory.update{ list -> list - item}
    }

    fun addOnlineItems(){
        viewModelScope.launch {
            try{
            val call = ApiClient.apiService.getItems()
            call.enqueue(object : Callback<List<Item>> {
                override fun onResponse(call: Call<List<Item>>, response: Response<List<Item>>) {
                    if (response.isSuccessful) {
                        val itemsList = response.body()
                        if (itemsList != null) {
                            itemsList.forEach { item ->
                                _inventory.update { list -> list + item }
                            }
                        }
                    } else {
                        println("Response body is null")
                    }
                }

                override fun onFailure(call: Call<List<Item>>, t: Throwable) {
                    println("Request failed")
                    Log.e("InventoryViewModel", "Exception: ${t.message}", t)
                }

            })
        }catch(e: Exception){
                Log.e("InventoryViewModel", "Exception: ${e.message}", e)
        }
        }
    }




}
