package com.example.inventorymanagementsystem.model

class InventoryRepository {
    var inventory = Inventory()


    fun addItem(item: Item){
        inventory.items.add(item)
    }

    fun removeItem(item: Item){
        inventory.items.remove(item)
    }

    fun updateItem(item: Item){
        val index = inventory.items.indexOf(item)
    }
    fun specificItem(name: String): Item?{
        return inventory.items.find { it.name == name }

    }
}