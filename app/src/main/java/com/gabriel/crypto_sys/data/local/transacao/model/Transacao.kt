package com.gabriel.crypto_sys.data.local.transacao.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Transacao(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    var id: Long,
    @ColumnInfo(name = "CODIGO")
    val cod: String? = null,
    @ColumnInfo(name = "QTD")
    val quantidade: Int? = null,
    @ColumnInfo(name = "VALOR")
    val valor: Double? = null,
    @ColumnInfo(name = "ID_CARTEIRA")
    val carteiraId: String? = null,
) : Serializable
