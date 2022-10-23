package com.gabriel.crypto_sys.ui.carteira.validaCarteira

import com.gabriel.crypto_sys.data.local.carteira.model.Carteira
import com.gabriel.crypto_sys.utils.state.ResourceState

class ValidaSaque : ValidaCarteira {
    override fun regraCalculoCarteira(valor: Int?, carteira: Carteira): ResourceState<Carteira> {
        if (valor.toString().isEmpty()) {
            return ResourceState.Error(message = "O valor digitado é inválido.")
        }

        if (valor.toString() > carteira.saldo.toString()) {
            return ResourceState.Error(message = "O valor digitado é maior que o saldo atual.")
        }

        return ResourceState.Success(data = carteira.apply {
            this.saldo -= valor!!
        })
    }
}