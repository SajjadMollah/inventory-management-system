package com.example.inventorymanagementsystem.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.inventorymanagementsystem.model.Item


@Composable
fun AddProductScreen(
    onAddProduct: (Item) -> Unit,
    navController: NavHostController,
    modifier: Modifier = Modifier.fillMaxSize()
) {

    var product by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            AddProductTopBar("Inventory", navController)
        }
    ) { innerPadding ->

        Column(modifier = modifier.padding(innerPadding)) {
            TextField(
                value = product,
                onValueChange = { product = it },
                label = { Text("Enter a Product") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            TextField(
                value = price,
                onValueChange = { price = it },
                label = { Text("Enter a Price") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            TextField(
                value = quantity,
                onValueChange = { quantity = it },
                label = { Text("Enter Quantity") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            Button(
                onClick = {
                    onAddProduct.invoke(
                        Item(
                            product,
                            price.toDouble(),
                            quantity.toInt()
                        )
                    ); navController.popBackStack()
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Add Item")


            }


        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProductTopBar(pageName: String, navController: NavHostController, modifier: Modifier = Modifier){

    CenterAlignedTopAppBar(title = { Text(pageName)},
        navigationIcon = {
            IconButton(onClick ={navController.popBackStack()}){
                Icon(imageVector = Icons.Filled.ArrowBackIosNew, contentDescription = "BackButton")

            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Blue, // Change background color here
            titleContentColor = Color.White, // Change title text color here
            navigationIconContentColor = Color.White
        )

    )
}

