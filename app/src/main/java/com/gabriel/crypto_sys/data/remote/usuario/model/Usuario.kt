package com.gabriel.crypto_sys.data.remote.usuario.model

import java.io.Serializable

data class Usuario(
    val email: String,
    val senha: String
) : Serializable
