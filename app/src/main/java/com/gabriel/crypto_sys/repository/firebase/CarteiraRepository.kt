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
        carteira?.let { dao.salva(it) }
    }

    fun verifyIfExists(carteiraId: String): Boolean {
        return dao.verifyIfCarteiraExists(carteiraId = carteiraId)
    }

    suspend fun getCarteiraAtual(usuarioId: String): Carteira? = withContext(IO) {
        dao.getCarteira(id = usuarioId)
    }
}