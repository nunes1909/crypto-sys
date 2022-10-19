package com.gabriel.crypto_sys.model

import java.io.Serializable

data class Usuario(
    val email: String,
    val senha: String
) : Serializable
