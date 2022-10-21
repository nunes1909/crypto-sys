package com.gabriel.crypto_sys.repository.firebase

import com.gabriel.crypto_sys.data.local.carteira.dao.CarteiraDao
import com.gabriel.crypto_sys.data.local.carteira.model.Carteira
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CarteiraRepository(private val dao: CarteiraDao) {

    fun salvaCarteira(carteira: Carteira?) {
        CoroutineScope(Dispatchers.IO).launch {
            carteira?.let { dao.salva(it) }
        }
    }

    fun verifyIfExists(carteiraId: String?): Boolean {
        var exists = true
        CoroutineScope(Dispatchers.IO).launch {
            carteiraId?.let {
                if (!dao.verifyIfCarteiraExists(carteiraId = it)) {
                    salvaCarteira(Carteira(id = carteiraId))
                    exists = false
                }
            }
        }
        return exists
    }

    suspend fun getCarteiraAtual(usuarioId: String): Carteira? = withContext(IO) {
        dao.getCarteira(id = usuarioId)
    }
}