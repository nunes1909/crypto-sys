package com.gabriel.crypto_sys.repository.carteira

import com.gabriel.crypto_sys.data.local.carteira.dao.CarteiraDao
import com.gabriel.crypto_sys.data.local.carteira.model.Carteira
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class CarteiraRepository(private val dao: CarteiraDao) {

    fun salvaCarteira(carteira: Carteira?) {
        carteira?.let { dao.salva(it) }
    }

    suspend fun getCarteiraAtual(usuarioId: String): Flow<Carteira?> = withContext(IO) {
        dao.getCarteira(id = usuarioId)
    }

    fun verifyIfExists(carteiraId: String): Boolean {
        return dao.verifyIfCarteiraExists(carteiraId = carteiraId)
    }
}