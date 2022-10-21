package com.gabriel.crypto_sys.ui.carteira

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gabriel.crypto_sys.data.local.carteira.model.Carteira
import com.gabriel.crypto_sys.repository.firebase.CarteiraRepository
import com.gabriel.crypto_sys.repository.firebase.FirebaseRepository

class CarteiraViewModel(
    private val firebaseRepository: FirebaseRepository,
    private val carteiraRepository: CarteiraRepository
) : ViewModel() {

    private val _carteira = MutableLiveData<Carteira?>()
    val carteira = _carteira as LiveData<Carteira?>

    suspend fun getCarteiraAtual() {
        firebaseRepository.getUserAtual()?.uid?.let {
            _carteira.value = carteiraRepository.getCarteiraAtual(it)
        }
    }
}
