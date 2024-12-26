package com.example.inventorymanagementsystem.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.inventorymanagementsystem.view.AddProductScreen
import com.example.inventorymanagementsystem.view.InventoryViewScreen
import com.example.inventorymanagementsystem.viewmodel.InventoryViewModel

@Composable
fun AppNav(){
    val navController = rememberNavController()
    val inventoryViewModel = InventoryViewModel()
    NavHost(navController=navController, startDestination = "home"){
        composable("home"){
            InventoryViewScreen(inventoryViewModel, navController)}
        composable("addProduct"){
            AddProductScreen(onAddProduct = {inventoryViewModel.addItem(it)}, navController)
        }

    }
}