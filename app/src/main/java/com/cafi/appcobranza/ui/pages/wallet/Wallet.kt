package com.cafi.appcobranza.ui.pages.wallet

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cafi.appcobranza.model.WalletEntity
import com.cafi.appcobranza.model.WalletSelected
import com.cafi.appcobranza.service.WalletServiceImp
import com.cafi.appcobranza.utils.Status

class Wallet {

    private var filterOne: List<WalletEntity> by mutableStateOf(emptyList())
    private var filterOThow: List<WalletEntity> by mutableStateOf(emptyList())
    @Composable
    fun WalletContent(isFilter: Boolean, search: String, rs: (List<WalletSelected>)-> Unit){
        val walletView: WalletServiceImp = viewModel()

        when(val status = walletView.status.collectAsState().value){
            is Status.Loading -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ){
                    CircularProgressIndicator(
                        color = Color.Blue
                    )
                }
            }
            is Status.Success -> {
                LaunchedEffect(search){}
                val wallet: List<WalletEntity> = status.data as List<WalletEntity>
                var selectedItems by remember { mutableStateOf(emptyList<WalletSelected>()) }
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                    .fillMaxWidth()
                ){
                    when(isFilter){
                        true -> {
                            val wallets = getWalletFilterOne(wallet, search)
                            LazyColumn(){
                                itemsIndexed(wallets){
                                        item, result ->
                                    val selectableItem = selectedItems.find { it.wallet.id == result.id }
                                        ?: WalletSelected(result)
                                    rs(selectedItems)
                                    Surface(
                                        color = MaterialTheme.colors.background,
                                        shape = RoundedCornerShape(4.dp),
                                        modifier = Modifier
                                            .padding(8.dp),
                                    ) {

                                        Column(
                                            modifier = Modifier
                                                .padding(horizontal = 8.dp)
                                                .background(Color.Transparent)
                                                .fillMaxWidth()
                                        ) {
                                            ListTile(wallet = result)
                                        }
                                    }
                                    Box(
                                        contentAlignment = Alignment.CenterEnd,
                                        modifier = Modifier
                                            .padding(bottom = 10.dp)
                                            .fillMaxWidth())
                                    {
                                        Row() {
                                            Box(
                                                contentAlignment = Alignment.Center,
                                                modifier = Modifier
                                                    .height(35.dp)
                                                    .width(135.dp)
                                            ) {
                                                Row() {
                                                    Text(
                                                        modifier = Modifier.padding(top = 9.dp),
                                                        text = "Seleccionar",
                                                        fontSize = 12.sp,
                                                        fontWeight = FontWeight.Bold,
                                                        color = Color.Gray
                                                    )
                                                    Checkbox(
                                                        checked = selectableItem.isSeleced,
                                                        onCheckedChange = {isChecked ->
                                                            selectableItem.isSeleced = isChecked
                                                            selectedItems = if (isChecked){
                                                                selectedItems + selectableItem
                                                            }else{
                                                                selectedItems - selectableItem
                                                            }
                                                        },
                                                        colors = CheckboxDefaults.colors(
                                                            checkedColor = Color(150,44,0),
                                                            uncheckedColor = Color.Gray,
                                                        ),
                                                    )
                                                }

                                            }
                                            TextButton(
                                                onClick = {
                                                    selectedItems.filter { it.isSeleced }
                                                    selectedItems.forEach {
                                                        Log.e("Objetos seleccionados: ",it.toString() )
                                                    }
                                                },
                                                modifier = Modifier
                                                    .height(35.dp)
                                                    .padding(end = 5.dp)
                                            ) {
                                                Text(
                                                    text = "Ver detalles",
                                                    fontSize = 12.sp,
                                                    color = Color.Gray
                                                )
                                            }
                                        }
                                    }
                                    Divider(startIndent = 2.dp)

                                }
                            }
                        }
                        false -> {
                            val wallets = getWalletFilterThow(wallet, search)
                            LazyColumn(){
                                itemsIndexed(wallets){
                                        item, result ->
                                    val selectableItem = selectedItems.find { it.wallet.id == result.id }
                                        ?: WalletSelected(result)
                                    rs(selectedItems)
                                    Surface(
                                        color = MaterialTheme.colors.background,
                                        shape = RoundedCornerShape(4.dp),
                                        modifier = Modifier
                                            .padding(8.dp),
                                    ) {

                                        Column(
                                            modifier = Modifier
                                                .padding(horizontal = 8.dp)
                                                .background(Color.Transparent)
                                                .fillMaxWidth()
                                        ) {
                                            ListTile(wallet = result)
                                        }
                                    }
                                    Box(
                                        contentAlignment = Alignment.CenterEnd,
                                        modifier = Modifier
                                            .padding(bottom = 10.dp)
                                            .fillMaxWidth())
                                    {
                                        Row() {
                                            Box(
                                                contentAlignment = Alignment.Center,
                                                modifier = Modifier
                                                    .height(35.dp)
                                                    .width(135.dp)
                                            ) {
                                                Row() {
                                                    Text(
                                                        modifier = Modifier.padding(top = 9.dp),
                                                        text = "Seleccionar",
                                                        fontSize = 12.sp,
                                                        fontWeight = FontWeight.Bold,
                                                        color = Color.Gray
                                                    )
                                                    Checkbox(
                                                        checked = selectableItem.isSeleced,
                                                        onCheckedChange = {isChecked ->
                                                            selectableItem.isSeleced = isChecked
                                                            selectedItems = if (isChecked){
                                                                selectedItems + selectableItem
                                                            }else{
                                                                selectedItems - selectableItem
                                                            }
                                                        },
                                                        colors = CheckboxDefaults.colors(
                                                            checkedColor = Color(150,44,0),
                                                            uncheckedColor = Color.Gray,
                                                        ),
                                                    )
                                                }

                                            }
                                            TextButton(
                                                onClick = {
                                                    selectedItems.filter { it.isSeleced }
                                                    selectedItems.forEach {
                                                        Log.e("Objetos seleccionados: ",it.toString() )
                                                    }
                                                },
                                                modifier = Modifier
                                                    .height(35.dp)
                                                    .padding(end = 5.dp)
                                            ) {
                                                Text(
                                                    text = "Ver detalles",
                                                    fontSize = 12.sp,
                                                    color = Color.Gray
                                                )
                                            }
                                        }
                                    }
                                    Divider(startIndent = 2.dp)

                                }
                            }
                        }
                    }
                }
            }
            is Status.Error -> {
                val error: String = status.error
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ){
                    Text(text = error)
                }
            }
        }
    }

    @Composable
    fun ListTile(
        wallet: WalletEntity
    ) {
        var sizeText = 13.sp
        Text(
            text =wallet.nombreCliente,
            style = MaterialTheme.typography.body1,
            color = Color.Black
        )
        Row() {
            Text(
                text ="Codigo: ",
                style = MaterialTheme.typography.body1,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = sizeText
            )
            Text(
                text = wallet.codigoCliente,
                style = MaterialTheme.typography.body1,
                color = Color.Black,
                fontSize = sizeText
            )
        }
        Row() {
            Text(
                text = "Domicilio: ",
                style = MaterialTheme.typography.body1,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = sizeText
            )
            Text(
                text = wallet.direccion,
                style = MaterialTheme.typography.body1,
                color = Color.Black,
                fontSize = sizeText
            )
        }
        Row {
            Text(
                text = "num. Credito: ",
                style = MaterialTheme.typography.body1,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = sizeText
            )
            Text(
                text = wallet.numeroCredito,
                style = MaterialTheme.typography.body1,
                color = Color.Black,
                fontSize = sizeText
            )
        }
        Row {
            Text(
                text = "Días vencidos: ",
                style = MaterialTheme.typography.body1,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = sizeText
            )
            Text(
                text = "${wallet.diasAtraso}",
                style = MaterialTheme.typography.body1,
                color = Color.Black,
                fontSize = sizeText
            )
        }
        Row  {
            Text(
                text = "Cartera riesgo: ",
                style = MaterialTheme.typography.body1,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = sizeText
            )
            Text(
                text = "$ ${wallet.carteraRiesgo}",
                style = MaterialTheme.typography.body1,
                color = Color.Black,
                fontSize = sizeText
            )
        }

    }
    private fun getWalletFilterOne(wallet: List<WalletEntity>, search: String): List<WalletEntity> {
        filterOne =  wallet.filter { it.diasAtraso > 0 }
        if (search != ""){
            return filterOne.filter { it.nombreCliente.contains(search.uppercase()) || it.codigoCliente.contains(search) }
        }
        return filterOne
    }
    private fun getWalletFilterThow(wallet: List<WalletEntity>, search: String): List<WalletEntity> {
        filterOThow =  wallet.filter { it.diasAtraso <= 0 }
        if (search != ""){
            return filterOThow.filter { it.nombreCliente.contains(search.uppercase()) || it.codigoCliente.contains(search)}
        }
        return filterOThow
    }
}


