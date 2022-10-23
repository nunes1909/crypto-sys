package com.gabriel.crypto_sys.ui.carteira.validaCarteira

import com.gabriel.crypto_sys.data.local.carteira.model.Carteira
import com.gabriel.crypto_sys.utils.state.ResourceState

class ValidaDeposito : ValidaCarteira {
    override fun regraCalculoCarteira(valor: Int, carteira: Carteira): ResourceState<Carteira> {
        if (valor <= 0) {
            return ResourceState.Error(message = "O valor digitado é inválido.")
        }

        return ResourceState.Success(data = carteira.apply {
            this.saldo += valor
        })
    }
}
