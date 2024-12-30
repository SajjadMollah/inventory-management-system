package com.example.inventorymanagementsystem.model

data class Item(val name:String, val price:Double, val quantity:Int, val imageURL: String) {
    val id: Int? = null

}