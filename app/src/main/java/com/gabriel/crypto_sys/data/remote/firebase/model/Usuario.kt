package com.gabriel.crypto_sys.data.remote.firebase.model

import java.io.Serializable

data class Usuario(
    val email: String,
    val senha: String
) : Serializable
