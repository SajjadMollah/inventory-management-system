package com.example.inventorymanagementsystem.view



import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.example.inventorymanagementsystem.activity.AddActivity
import com.example.inventorymanagementsystem.activity.MainActivity
import com.example.inventorymanagementsystem.model.Inventory
import com.example.inventorymanagementsystem.model.Item
import com.example.inventorymanagementsystem.ui.theme.InventoryManagementSystemTheme
import com.example.inventorymanagementsystem.viewmodel.InventoryViewModel



@Composable
fun InventoryView(inventoryViewModel: InventoryViewModel, modifier: Modifier = Modifier.fillMaxSize()){

    val inventory by inventoryViewModel.inventory.collectAsState()


    Column{
        Text(text = "Inventory", modifier = Modifier.align(Alignment.CenterHorizontally))

        if (inventory.isEmpty()){
            Text(text = "No items", modifier = Modifier)
        }
        else {
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
        val context = LocalContext.current
        Button(onClick = {val intent = Intent(context, AddActivity::class.java)
        context.startActivity(intent)}){
            Text(text = "Add Item")

        }


    }


}





@Composable
fun AddingProduct(modifier: Modifier = Modifier, onAddProduct: (Item) -> Unit){

    var product by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("")}
    var quantity by remember { mutableStateOf("") }

Column {
    TextField(
        value = product,
        onValueChange = { product= it },
        label = { Text("Enter a Product") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
    TextField(
        value = price,
        onValueChange = { price= it },
        label = { Text("Enter a Price") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
    TextField(
        value = quantity,
        onValueChange = { quantity= it },
        label = { Text("Enter Quantity") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
    Button(onClick = {onAddProduct.invoke(Item(product, price.toDouble(), quantity.toInt()))}, modifier= Modifier.align(Alignment.CenterHorizontally)) {
        Text(text = "Add Item")


    }
    val context = LocalContext.current
    Button(onClick = {val intent = Intent(context, MainActivity::class.java)
        context.startActivity(intent)}){
        Text(text = "Return main page")

    }

}


}

@Preview(showBackground = true)
@Composable
fun InventoryViewPreview() {
    InventoryManagementSystemTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            val inventoryViewModel : InventoryViewModel = InventoryViewModel()
            InventoryView(inventoryViewModel)
//            AddingProduct (onAddProduct = {inventoryViewModel.addItem(it)})

            }


        }

    }
