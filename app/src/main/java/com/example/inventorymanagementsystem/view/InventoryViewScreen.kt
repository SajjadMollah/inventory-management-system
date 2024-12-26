package com.example.inventorymanagementsystem.view


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.inventorymanagementsystem.navigation.AppNav
import com.example.inventorymanagementsystem.ui.theme.InventoryManagementSystemTheme
import com.example.inventorymanagementsystem.viewmodel.InventoryViewModel


@Composable
fun InventoryViewScreen(
    inventoryViewModel: InventoryViewModel,
    navController: NavHostController, modifier: Modifier = Modifier.fillMaxSize()
) {

    val inventory by inventoryViewModel.inventory.collectAsState()


    Column {
        Text(text = "Inventory", modifier = Modifier.align(Alignment.CenterHorizontally))

        if (inventory.isEmpty()) {
            Text(text = "No items", modifier = Modifier)
        } else {
            inventory.forEach { item ->
                Row {

                    Text(
                        text = "Product: ${item?.name ?: "No item found"}!",
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
//        AddingProduct(onAddProduct = {inventoryViewModel.addItem(it)})
        Button(onClick = { navController.navigate("addProduct") }) {
            Text("Go to products page")

        }


    }


}


@Preview(showBackground = true)
@Composable
fun InventoryViewPreview() {
    InventoryManagementSystemTheme {
        Surface(modifier = Modifier.fillMaxSize()) {

            AppNav()


        }

    }
}