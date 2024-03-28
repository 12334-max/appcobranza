package com.cafi.appcobranza.utils

sealed class Status<out T> {
    object Loading : Status<Nothing>()
    data class Success<T>(val data: List<T>) : Status<T>()
    data class Error(val error: String) : Status<Nothing>()
}
