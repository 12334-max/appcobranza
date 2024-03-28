package com.cafi.appcobranza.ui.layout

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Checklist
import androidx.compose.material.icons.filled.CloudUpload
import androidx.compose.material.icons.filled.DocumentScanner
import androidx.compose.material.icons.filled.House
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Wallet
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cafi.appcobranza.ui.pages.risk.Risk
import com.cafi.appcobranza.ui.pages.visits.Visits
import com.cafi.appcobranza.ui.pages.wallet.Wallet
import com.cafi.appcobranza.utils.SelectorDate

class MainLayout{

    var isFilter by mutableStateOf(true)


    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    fun AppContent(){
        val scaffoldState = rememberScaffoldState()
        var selectedTabIndex by remember { mutableStateOf(0) }
        var isVisibleCalendar by remember { mutableStateOf(false) }

        Scaffold (
            scaffoldState = scaffoldState,
            topBar = {
                AppBar(selectedTabIndex = selectedTabIndex, isVisibleCalendar){
                    isVisibleCalendar = !isVisibleCalendar
                }
            },
            bottomBar = {
                when(selectedTabIndex){
                    0 -> {
                        BottomAppBar(
                            contentColor = Color(255,44,0),
                            backgroundColor = Color(255,44,0),
                            content = {
                                FooterBar(selectedTabIndex)
                            }
                        )
                    }
                    1 -> {
                        BottomAppBar(
                            contentColor = Color(255,44,0),
                            backgroundColor = Color(255,44,0),
                            content = {
                                FooterBar(selectedTabIndex)
                            }
                        )
                    }
                }
            },
            content = {
                NavBar(
                    selectedTabIndex = selectedTabIndex,
                    onTabSelected = {
                            index -> selectedTabIndex = index
                    },
                    isVisibleCalendar,
                )
            }
        )
    }

    @Composable
    fun AppBar(
        selectedTabIndex: Int,
        isVisibleCalendar: Boolean,
        onClick: ()->Unit
    ) {
        TopAppBar(
            backgroundColor = Color(255, 44, 0),
            title = { Text(text = "Cobranza", color = Color.White) },
            actions = {
                when (selectedTabIndex) {
                    0 -> VisitsActions(isVisibleCalendar){
                        onClick()
                    }
                    1 -> WalletActions()
                    else -> {}
                }
            },
            elevation = 0.dp
        )
    }


    @Composable
    fun NavBar(
        selectedTabIndex: Int,
        onTabSelected: (Int) -> Unit,
        isVisibleCalendar: Boolean,
    ){

        Column {
            TabRow(
                selectedTabIndex = selectedTabIndex,
                backgroundColor = Color(255, 44, 0),
                contentColor = Color.White
            ) {
                Tab(
                    selected = selectedTabIndex == 0,
                    onClick = { onTabSelected(0) },
                    text = { Text(text = "Visitas") },
                    icon = {
                        Icon(imageVector = Icons.Filled.House, contentDescription = null)
                    }
                )
                Tab(
                    selected = selectedTabIndex == 1,
                    onClick = { onTabSelected(1) },
                    text = { Text(text = "Cartera") },
                    icon = {
                        Icon(imageVector = Icons.Filled.Wallet, contentDescription = null)
                    }
                )
                Tab(
                    selected = selectedTabIndex == 2,
                    onClick = { onTabSelected(2) },
                    text = { Text(text = "Reporte") },
                    icon = {
                        Icon(imageVector = Icons.Filled.DocumentScanner, contentDescription = null)
                    }
                )
            }
            when (selectedTabIndex) {
                0 -> {
                   Visits().VisitsContent(isVisibleCalendar, isFilter)
                }
                1 -> {
                    Wallet().WalletContent()
                }
                2 -> {
                    Risk().RiskContent()
                }
            }
        }
    }

    @Composable
    fun FooterBar(
        selectedTabIndex: Int
    ){
        when(selectedTabIndex){
            0 -> {
                FooterVisits()
            }
            1 -> {
                FooterWallet()
            }
        }
    }

    @Composable
    fun FooterVisits(){
        var selectedTabIndex by remember { mutableStateOf(0) }

        Column {
            TabRow(
                selectedTabIndex = selectedTabIndex,
                backgroundColor = Color(255,44,0),
                contentColor = Color.White
            ) {
                Tab(
                    selected = selectedTabIndex == 0,
                    onClick = {
                        selectedTabIndex = 0
                        isFilter = true
                              },
                    text = { Text(text = "Visitas") },
                    icon = {
                        Icon(imageVector = Icons.Filled.Checklist, contentDescription = null)
                    }
                )
                Tab(
                    selected = selectedTabIndex == 1,
                    onClick = {
                        selectedTabIndex = 1
                        isFilter = false
                              },
                    text = { Text(text = "Promesas") },
                    icon = {
                        Icon(imageVector = Icons.Filled.Checklist, contentDescription = null)
                    }
                )
            }
        }
    }

    @Composable
    fun FooterWallet(){
        var selectedTabIndex by remember { mutableStateOf(0) }

        Column {
            TabRow(
                selectedTabIndex = selectedTabIndex,
                backgroundColor = Color(255,44,0),
                contentColor = Color.White
            ) {
                Tab(
                    selected = selectedTabIndex == 0,
                    onClick = { selectedTabIndex = 0 },
                    text = { Text(text = "Riesgo") },
                    icon = {
                        Icon(imageVector = Icons.Filled.Checklist, contentDescription = null)
                    }
                )
                Tab(
                    selected = selectedTabIndex == 1,
                    onClick = { selectedTabIndex = 1 },
                    text = { Text(text = "Vigente") },
                    icon = {
                        Icon(imageVector = Icons.Filled.Checklist, contentDescription = null)
                    }
                )
            }
        }
    }

    // Define las acciones para cada pestaña
    @Composable
    fun VisitsActions( isVisibleCalendar: Boolean, onToggleCalendarVisibility: () -> Unit) {

        IconButton(
            onClick = onToggleCalendarVisibility
        ) {
            Icon(imageVector = Icons.Filled.CalendarMonth, contentDescription = null, tint = Color.White)
        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Filled.CloudUpload, contentDescription = null, tint = Color.White)
        }
    }


    @Composable
    fun WalletActions() {
        val context = LocalContext.current
        var isVisible by remember { mutableStateOf(false) }
        var buscar by remember { mutableStateOf("") }
        val opnCalendar = SelectorDate()
        when(isVisible) {
              true -> {
                  OutlinedTextField(
                      value = buscar,
                      onValueChange = {
                                      buscar = it
                      },
                      enabled = true,
                      placeholder = { Text(text = "Buscar")},
                      trailingIcon = {
                          IconButton(onClick = {
                              isVisible = false
                          }){
                              Icon(imageVector = Icons.Filled.Search, contentDescription = null)
                          }
                      },
                      keyboardOptions = KeyboardOptions(
                          keyboardType = KeyboardType.Text,
                          imeAction = ImeAction.Search
                      ),
                      modifier = Modifier
                          .width(250.dp)
                          .height(60.dp)
                          .padding(top = 5.dp),
                      singleLine = true,
                      shape = CircleShape
                  )
              }
              else -> {}
          }

        when(isVisible){
            false -> {
                IconButton(onClick = { isVisible = true }) {
                    Icon(imageVector = Icons.Filled.Search, contentDescription = null, tint = Color.White)
                }
            }

            else -> {}
        }

        IconButton(onClick = {
            Log.d("MyScreen", "IconButton clicked")
            opnCalendar.openCalendar(context){
                result -> Log.d("Fecha Seleccionada: ", result )
            }
        }) {
            Icon(imageVector = Icons.Filled.CalendarMonth, contentDescription = null, tint = Color.White)
        }
    }

}