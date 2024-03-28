package com.cafi.appcobranza.service

import com.cafi.appcobranza.model.ResponseAPI
import com.cafi.appcobranza.model.WalletEntity
import retrofit2.http.Body
import retrofit2.http.POST

interface Services {
    @POST("Cartera/getCartera")
    suspend fun postWallet(@Body data: Any): ResponseAPI<List<WalletEntity>>
}