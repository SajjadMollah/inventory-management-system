package com.example.inventorymanagementsystem.activity


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavController
import com.example.inventorymanagementsystem.navigation.AppNav

import com.example.inventorymanagementsystem.ui.theme.InventoryManagementSystemTheme
import com.example.inventorymanagementsystem.viewmodel.InventoryViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inventoryViewModel : InventoryViewModel = InventoryViewModel()
        val navController: NavController
        enableEdgeToEdge()
        setContent {
            InventoryManagementSystemTheme {
                AppNav()
                }
            }
        }
    }

