package com.cafi.appcobranza.service

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cafi.appcobranza.model.WalletEntity
import com.cafi.appcobranza.utils.RetrofitInstance
import com.cafi.appcobranza.utils.Status
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class WalletServiceImp : ViewModel() {
    private val retrofit = RetrofitInstance
    private val service: Services = retrofit.service

    private val _estado = MutableStateFlow<Status<WalletEntity>>(Status.Loading)
    val status: StateFlow<Status<Any>> = _estado

    val data = mapOf(
        "token" to "",
        "data" to mapOf(
            "usuario" to "gsanchezp"
        )
    )

    init {
        getAllWallet(data)
    }

    private fun getAllWallet(data: Any){
        viewModelScope.launch {
            try {
                val response = service.postWallet(data)
                _estado.value = Status.Success(response.data)
            }catch (e: Exception){
                _estado.value = Status.Error(e.toString())
            }
        }
    }
}