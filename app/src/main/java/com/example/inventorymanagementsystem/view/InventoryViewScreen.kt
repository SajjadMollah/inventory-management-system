package com.example.inventorymanagementsystem.view


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.inventorymanagementsystem.navigation.AppNav
import com.example.inventorymanagementsystem.ui.theme.InventoryManagementSystemTheme
import com.example.inventorymanagementsystem.viewmodel.InventoryViewModel


@Composable
fun InventoryViewScreen(
    inventoryViewModel: InventoryViewModel,
    navController: NavHostController,
    modifier: Modifier = Modifier.fillMaxSize().background(Color.DarkGray)
) {
    val inventory by inventoryViewModel.inventory.collectAsState()
    val configuration  = LocalConfiguration.current

    Scaffold(
        topBar = {
            ScreenTopBar("Inventory", navController)
        }
    ) { innerPadding ->
        Column(modifier = modifier.padding(innerPadding)) {

            if (inventory.isEmpty()) {
                Text(text = "No items", modifier = Modifier.align(Alignment.CenterHorizontally).padding(0.dp, configuration.screenHeightDp.dp/3, 0.dp, 0.dp), color = Color.White,  fontSize = 23.sp)
            } else {
                inventory.forEach { item ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            var imageUrl: String = "https://static.toiimg.com/thumb/53110049.cms?width=1200&height=900"
                            if(item.imageUrl != ""){
                                imageUrl = item.imageUrl
                            }
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(imageUrl)
                                    .crossfade(true)
                                    .build(),
                                contentDescription = "Product Image",
                                modifier = Modifier
                                    .size(80.dp)
                                    .clip(RoundedCornerShape(8.dp)),
                                contentScale = ContentScale.Crop
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Column {
                                Text(text = item.name, fontWeight = FontWeight.Bold)
                                Text(text = "$${item.price}")
                                Text(text = "Quantity: ${item.quantity}")

                            }
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                Spacer(modifier = Modifier.width(8.dp))
                                Button(onClick = { inventoryViewModel.removeItem(item) },
                                    colors= ButtonDefaults.buttonColors(containerColor = Color.Gray)) {
                                    Icon(
                                        imageVector = Icons.Filled.Remove,
                                        contentDescription = "Remove Product Button"
                                    )
                                }
                            }

                        }
                    }

//                    Row {
//                        Text(
//                            text = "Product: ${item?.name ?: "No item found"}",
//                            modifier = Modifier.padding(0.dp, 0.dp, 10.dp, 0.dp)
//                        )
//
//                        Text(
//                            text = "Price: ${item?.price ?: 0.0}",
//                            modifier = Modifier.padding(0.dp, 0.dp, 10.dp, 0.dp)
//                        )
//                        Text(
//                            text = "Quantity: ${item?.quantity ?: 0}",
//                            modifier = Modifier.padding(0.dp, 0.dp, 10.dp, 0.dp)
//                        )
//                        Button(onClick = {inventoryViewModel.removeItem(item)}){
//                            Icon(imageVector = Icons.Filled.Remove, contentDescription = "RemoveProductButton")
//                        }
//                    }
                }
            }
            Spacer(modifier = Modifier.weight(1f))

            Button(onClick = { navController.navigate("addProduct") },
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
            ) {
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
            containerColor = Color.Gray, // Change background color here
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