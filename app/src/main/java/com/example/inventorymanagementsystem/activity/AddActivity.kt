package com.example.inventorymanagementsystem.activity


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

import com.example.inventorymanagementsystem.ui.theme.InventoryManagementSystemTheme
import com.example.inventorymanagementsystem.view.AddingProduct
import com.example.inventorymanagementsystem.view.InventoryView
import com.example.inventorymanagementsystem.viewmodel.InventoryViewModel

class AddActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inventoryViewModel : InventoryViewModel = InventoryViewModel()
        enableEdgeToEdge()
        setContent {
            InventoryManagementSystemTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    AddingProduct(modifier = Modifier.padding(innerPadding), onAddProduct = {inventoryViewModel.addItem(it)})
                }
            }
        }
    }
}