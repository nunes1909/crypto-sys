package com.gabriel.crypto_sys.repository.firebase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gabriel.crypto_sys.data.local.carteira.model.Carteira
import com.gabriel.crypto_sys.data.remote.firebase.model.Usuario
import com.gabriel.crypto_sys.repository.carteira.CarteiraRepository
import com.gabriel.crypto_sys.utils.state.ResourceState
import com.google.firebase.auth.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class FirebaseRepository(
    private val firebaseAuth: FirebaseAuth,
    private val carteiraRepository: CarteiraRepository
) {
    fun cadastraUsuario(usuario: Usuario): LiveData<ResourceState<Boolean>> {
        val liveData = MutableLiveData<ResourceState<Boolean>>()
        try {
            firebaseAuth.createUserWithEmailAndPassword(usuario.email, usuario.senha)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        salvaCarteira(task.result.user!!.uid)
                        liveData.value = ResourceState.Success(true)
                    } else {
                        val messageError = getErrorCadastro(exception = task.exception)
                        liveData.value = ResourceState.Error(data = false, message = messageError)
                    }
                }
        } catch (e: IllegalAccessException) {
            liveData.value =
                ResourceState.Error(data = false, message = "E-mail ou senha inválidos")
        }
        return liveData
    }

    private fun getErrorCadastro(exception: Exception?): String = when (exception) {
        is FirebaseAuthWeakPasswordException -> "Senha precisa de pelo menos 6 dígitos"
        is FirebaseAuthInvalidCredentialsException -> "E-mail inválido"
        is FirebaseAuthUserCollisionException -> "E-mail já cadastrado"
        else -> "Erro desconhecido"
    }

    fun autenticaUsuario(usuario: Usuario): LiveData<ResourceState<Boolean>> {
        val liveData = MutableLiveData<ResourceState<Boolean>>()
        try {
            firebaseAuth.signInWithEmailAndPassword(usuario.email, usuario.senha)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        autenticaCarteira(task.result.user!!.uid)
                        liveData.value = ResourceState.Success(data = true)
                    } else {
                        val messageError = getErrorAuth(task.exception)
                        liveData.value = ResourceState.Error(data = false, message = messageError)
                    }
                }
        } catch (e: IllegalArgumentException) {
            liveData.value = ResourceState.Error(
                data = false, message = "E-mail ou senha não pode ser vazio"
            )
        }
        return liveData
    }

    private fun autenticaCarteira(userId: String) = CoroutineScope(IO).launch {
        if (!carteiraRepository.verifyIfExists(userId)) {
            salvaCarteira(userId)
        }
    }

    private fun salvaCarteira(userId: String) {
        carteiraRepository.salvaCarteira(Carteira(id = userId))
    }

    private fun getErrorAuth(exception: java.lang.Exception?): String = when (exception) {
        is FirebaseAuthInvalidUserException,
        is FirebaseAuthInvalidCredentialsException -> "E-mail ou senha incorretos"
        else -> "Erro desconhecido"
    }

    fun getUserAtual(): FirebaseUser? {
        return firebaseAuth.currentUser
    }

    fun desloga() {
        firebaseAuth.signOut()
    }
}