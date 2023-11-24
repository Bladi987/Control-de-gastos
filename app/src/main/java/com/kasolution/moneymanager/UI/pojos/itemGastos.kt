package com.kasolution.moneymanager.UI.pojos

data class itemGastos(
    var id: String,
    var fecha: String,
    var ciudad: String,
    var tipoDoc: String,
    var nroDoc: String,
    var proveedor: String,
    var descripcion: String,
    var tipoGasto: String,
    var sustento: String,
    var monto: String,
    var archivo: String
)