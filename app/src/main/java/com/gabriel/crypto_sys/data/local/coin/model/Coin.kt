package com.gabriel.crypto_sys.data.local.coin.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "COIN")
data class Coin(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    var id: Long? = null,

    @ColumnInfo(name = "CODIGO")
    var cod: String? = null,

    @ColumnInfo(name = "TITULO")
    var title: String? = null
) : Serializable
