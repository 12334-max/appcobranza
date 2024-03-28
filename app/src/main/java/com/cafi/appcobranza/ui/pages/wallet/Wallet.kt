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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.cafi.appcobranza.service.WalletServiceImp
import com.cafi.appcobranza.utils.Status

class Wallet {

    private var clientsSelected: MutableList<WalletEntity> by mutableStateOf(mutableListOf())

    @Composable
    fun WalletContent(){
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

                val wallet: List<WalletEntity> = status.data as List<WalletEntity>
                val isSelected = remember { mutableMapOf<WalletEntity, Boolean>() }

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                    .fillMaxWidth()
                ){
                    LazyColumn(){
                        items(wallet){

                            item -> Surface(
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
                                    ListTile(wallet = item)
                                }
                            }
                                Box(
                                    contentAlignment = Alignment.CenterEnd,
                                    modifier = Modifier
                                        .padding(bottom = 10.dp)
                                        .fillMaxWidth())
                                {
                                    Row() {
                                        Log.e("isSelected[item]1", " ${isSelected[item]}", )
                                        when(isSelected[item]){
                                            null -> {
                                                Log.e("isSelected[item]2", " ${isSelected[item]}", )
                                                OutlinedButton(
                                                    modifier = Modifier
                                                        .height(30.dp)
                                                        .padding(end = 10.dp),
                                                    onClick = {
                                                        isSelected[item] = true
                                                        clientsSelected.add(item)
                                                    }
                                                )
                                                {
                                                    Text(
                                                        color = Color.DarkGray,
                                                        text = "Seleccionar",
                                                        fontSize = 12.sp
                                                    )
                                                }
                                            }
                                            false ->{
                                                OutlinedButton(
                                                    modifier = Modifier
                                                        .height(30.dp)
                                                        .padding(end = 10.dp),
                                                    onClick = {
                                                        isSelected[item] = true
                                                        clientsSelected.add(item)
                                                    }
                                                )
                                                {
                                                    Text(
                                                        color = Color.DarkGray,
                                                        text = "Seleccionar",
                                                        fontSize = 12.sp
                                                    )
                                                }
                                            }
                                            true -> {
                                                Log.e("isSelected[item]", " ${isSelected[item]}", )
                                                OutlinedButton(
                                                    modifier = Modifier
                                                        .height(30.dp)
                                                        .padding(end = 10.dp),
                                                    onClick = {
                                                        isSelected[item] = false
                                                        clientsSelected.removeIf { it.id == item.id }
                                                    }
                                                )
                                                {
                                                    Icon(imageVector = Icons.Filled.Cancel, contentDescription = null)
                                                }
                                            }
                                        }

                                        OutlinedButton(
                                            modifier = Modifier
                                                .height(30.dp),
                                            onClick = {
                                                clientsSelected.forEach {
                                                    Log.d("Cartera Selecionado: ", it.toString())
                                                }
                                            }
                                        )
                                        {
                                            Text(
                                                color = Color.DarkGray,
                                                text = "Ver Detalle",
                                                fontSize = 12.sp
                                            )
                                        }
                                    }
                                }
                                Divider(startIndent = 2.dp)
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
}

