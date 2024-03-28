package com.cafi.appcobranza.utils

import com.cafi.appcobranza.service.Services
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val service: Services by lazy {
        Retrofit.Builder()
            .baseUrl("https://tucafi.com.mx/Cobranza/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Services::class.java)
    }
}