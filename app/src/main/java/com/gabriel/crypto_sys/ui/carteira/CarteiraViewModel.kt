package com.gabriel.crypto_sys.ui.carteira

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gabriel.crypto_sys.data.local.carteira.model.Carteira
import com.gabriel.crypto_sys.repository.carteira.CarteiraRepository
import com.gabriel.crypto_sys.repository.firebase.FirebaseRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class CarteiraViewModel(private val carteiraRepository: CarteiraRepository) : ViewModel() {

    private val _carteira = MutableLiveData<Carteira?>()
    val carteira = _carteira as LiveData<Carteira?>

    suspend fun getCarteiraAtual(userId: String) {
        carteiraRepository.getCarteiraAtual(userId).collect { carteira ->
            _carteira.value = carteira
        }
    }

    fun salvaCarteira(carteira: Carteira) = CoroutineScope(IO).launch {
        carteiraRepository.salvaCarteira(carteira = carteira)
    }
}
