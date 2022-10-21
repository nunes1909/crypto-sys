package com.gabriel.crypto_sys.data.local.carteira.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Carteira(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "ID")
    val id: String,
    @ColumnInfo(name = "SALDO")
    var saldo: Double? = null,
) : Serializable
