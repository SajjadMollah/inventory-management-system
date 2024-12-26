package com.example.inventorymanagementsystem.view


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.inventorymanagementsystem.navigation.AppNav
import com.example.inventorymanagementsystem.ui.theme.InventoryManagementSystemTheme
import com.example.inventorymanagementsystem.viewmodel.InventoryViewModel


@Composable
fun InventoryViewScreen(
    inventoryViewModel: InventoryViewModel,
    navController: NavHostController,
    modifier: Modifier = Modifier.fillMaxSize()
) {
    val inventory by inventoryViewModel.inventory.collectAsState()

    Scaffold(
        topBar = {
            ScreenTopBar("Inventory", navController)
        }
    ) { innerPadding ->
        Column(modifier = modifier.padding(innerPadding)) {

            if (inventory.isEmpty()) {
                Text(text = "No items", modifier = Modifier.align(Alignment.CenterHorizontally))
            } else {
                inventory.forEach { item ->
                    Row {
                        Text(
                            text = "Product: ${item?.name ?: "No item found"}",
                            modifier = Modifier.padding(0.dp, 0.dp, 10.dp, 0.dp)
                        )

                        Text(
                            text = "Price: ${item?.price ?: 0.0}",
                            modifier = Modifier.padding(0.dp, 0.dp, 10.dp, 0.dp)
                        )
                        Text(
                            text = "Quantity: ${item?.quantity ?: 0}",
                            modifier = Modifier.padding(0.dp, 0.dp, 10.dp, 0.dp)
                        )
                    }
                }
            }

            Button(onClick = { navController.navigate("addProduct") }, modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text("Add a product")
                Icon(imageVector = Icons.Filled.Add, contentDescription = "AddButton")
            }
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenTopBar(pageName: String,navController: NavHostController, modifier: Modifier = Modifier){

    CenterAlignedTopAppBar(
        title = {
            Text(pageName)
                },

        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Blue, // Change background color here
            titleContentColor = Color.White, // Change title text color here
            navigationIconContentColor = Color.White
        )

    )
}



@Preview(showBackground = true)
@Composable
fun InventoryViewScreenPreview() {
    InventoryManagementSystemTheme {
        Surface(modifier = Modifier.fillMaxSize()) {

            AppNav()


        }

    }
}