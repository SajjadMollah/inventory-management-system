package com.example.inventorymanagementsystem.viewmodel

import androidx.lifecycle.ViewModel
import com.example.inventorymanagementsystem.model.InventoryRepository
import com.example.inventorymanagementsystem.model.Item
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class InventoryViewModel: ViewModel() {

    private val _inventory = MutableStateFlow<List<Item>>(listOf())

    val inventory: StateFlow<List<Item>> = _inventory

    fun addItem(item: Item){
        _inventory.update { list -> list + item }
    }

}
