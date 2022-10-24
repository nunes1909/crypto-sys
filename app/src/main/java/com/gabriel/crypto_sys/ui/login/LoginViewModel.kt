package com.gabriel.crypto_sys.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.gabriel.crypto_sys.data.remote.firebase.model.Usuario
import com.gabriel.crypto_sys.repository.firebase.FirebaseRepository
import com.gabriel.crypto_sys.utils.state.ResourceState

class LoginViewModel(private val repository: FirebaseRepository) : ViewModel() {

    fun autenticaUsuario(usuario: Usuario): LiveData<ResourceState<Boolean>> {
        return repository.autenticaUsuario(usuario = usuario)
    }

    fun estaLogado(): Boolean {
        if (repository.getUserAtual() != null) {
            return true
        }
        return false
    }

    fun desloga() {
        repository.desloga()
    }
}
