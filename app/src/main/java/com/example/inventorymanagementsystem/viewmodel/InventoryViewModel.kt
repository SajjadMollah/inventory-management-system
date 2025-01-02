package com.example.inventorymanagementsystem.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inventorymanagementsystem.model.InventoryRepository
import com.example.inventorymanagementsystem.model.Item
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class InventoryUiState(
    val isLoading: Boolean = false,
    val items: List<Item> = emptyList(),
    val error: String? = null
)

class InventoryViewModel(
    private val repository: InventoryRepository = InventoryRepository()
) : ViewModel() {

    val inventory: StateFlow<List<Item>> = repository.inventory

    init {
        loadItems()
    }

    private fun loadItems() {
        viewModelScope.launch {
            try {
                repository.fetchItems()
                    .onFailure { e ->
                        Log.e("InventoryViewModel", "Failed to fetch items", e)
                    }
            } catch (e: Exception) {
                Log.e("InventoryViewModel", "Exception while fetching items", e)
            }
        }
    }

    fun addItem(item: Item) {
        repository.addItem(item)
    }

    fun removeItem(item: Item) {
        repository.removeItem(item)
    }
}