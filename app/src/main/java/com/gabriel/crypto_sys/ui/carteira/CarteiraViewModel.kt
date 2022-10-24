package com.gabriel.crypto_sys.ui.carteira

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gabriel.crypto_sys.data.local.carteira.model.Carteira
import com.gabriel.crypto_sys.repository.carteira.CarteiraRepository
import com.gabriel.crypto_sys.repository.firebase.FirebaseRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class CarteiraViewModel(
    private val carteiraRepository: CarteiraRepository,
    firebaseAuth: FirebaseAuth
) : ViewModel() {

    init {
        getCarteiraAtual(firebaseAuth.uid!!)
    }

    private val _carteira = MutableLiveData<Carteira?>()
    val carteira = _carteira as LiveData<Carteira?>

    fun getCarteiraAtual(userId: String) = CoroutineScope(IO).launch {
        carteiraRepository.getCarteiraAtual(userId).collect { carteira ->
            _carteira.postValue(carteira)
        }
    }

    fun salvaCarteira(carteira: Carteira) = CoroutineScope(IO).launch {
        carteiraRepository.salvaCarteira(carteira = carteira)
    }
}
