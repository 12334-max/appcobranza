package com.cafi.appcobranza.model

import com.google.gson.annotations.SerializedName
import java.time.LocalDate

data class WalletEntity(
    @SerializedName("id") val id: String,
    @SerializedName("CodigoUsuario") val codigoUsuario: String,
    @SerializedName("CodigoCliente") val codigoCliente: String,
    @SerializedName("NombreCliente") val nombreCliente: String,
    @SerializedName("Telefono") val telefono: String,
    @SerializedName("Direccion") val direccion: String,
    @SerializedName("Colonia") val colonia: String,
    @SerializedName("CodigoPostal") val codigoPostal: String,
    @SerializedName("Estado") val estado: String,
    @SerializedName("Municipio") val municipio: String,
    @SerializedName("NombreLocalidad") val nombreLocalidad: String,
    @SerializedName("Latitud") val latitud: String,
    @SerializedName("Longitud") val longitud: String,
    @SerializedName("LatitudMunicipio") val latitudMunicipio: String,
    @SerializedName("LongitudMunicipio") val longitudMunicipio: String,
    @SerializedName("CodigoGrupo") val codigoGrupo: Int,
    @SerializedName("CodigoSucursal") val codigoSucursal: String,
    @SerializedName("NumeroCredito") val numeroCredito: String,
    @SerializedName("Consecutivo") val consecutivo: String,
    @SerializedName("Plazo") val plazo: Int,
    @SerializedName("ImporteCredito") val importeCredito: Double,
    @SerializedName("SaldoCapitalActual") val saldoCapitalActual: Double,
    @SerializedName("SaldoCapitalMora") val saldoCapitalMora: Double,
    @SerializedName("TasaMoratoria") val tasaMoratoria: Double,
    @SerializedName("ImporteMoraDiaria") val importeMoraDiaria: Double,
    @SerializedName("CarteraRiesgo") val carteraRiesgo: Double,
    @SerializedName("FechaDesembolso") val fechaDesembolso: String,
    @SerializedName("FechaVencimiento") val fechaVencimiento: String,
    @SerializedName("FechaUltimoPago") val fechaUltimoPago: String,
    @SerializedName("FechaProximaCuota") val fechaProximaCuota: String,
    @SerializedName("ImporteProximaCuota") val importeProximaCuota: Double,
    @SerializedName("CuotasVencidas") val cuotasVencidas: Int,
    @SerializedName("CuotasPagadas") val cuotasPagadas: Int,
    @SerializedName("DiasAtraso") val diasAtraso: Int,
    @SerializedName("DiasMoraEstimacion") val diasMoraEstimacion: Int,
    @SerializedName("SaldoCapital") val saldoCapital: Double,
    @SerializedName("SaldoInteresOrdinario") val saldoInteresOrdinario: Double,
    @SerializedName("SaldoMoratorio") val saldoMoratorio: Double,
    @SerializedName("CodigoClasificacion") val codigoClasificacion: String,
    @SerializedName("CodigoProducto") val codigoProducto: String,
    @SerializedName("Categoria") val categoria: String,
    @SerializedName("TasaIva") val tasaIva: Int,
    @SerializedName("Modelo") val modelo: String,
    @SerializedName("MarcaVehiculo") val marcaVehiculo: String,
    @SerializedName("TipoVehiculo") val tipoVehiculo: String,
    @SerializedName("NombreAval") val nombreAval: String,
    @SerializedName("TelefonoAval") val telefonoAval: String,
    @SerializedName("DomicilioAval") val domicilioAval: String,
    @SerializedName("Color") val color: String
)

data class WalletSelected(
    val wallet: WalletEntity,
    var isSeleced: Boolean = false,
    var fechaVisita: LocalDate = LocalDate.now()
)

