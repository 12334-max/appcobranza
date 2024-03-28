package com.cafi.appcobranza.model

data class ResponseAPI<T>(
val estatus: String,
val message: String,
val data: List<WalletEntity>
)
