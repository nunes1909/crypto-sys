package com.gabriel.crypto_sys.repository.firebase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gabriel.crypto_sys.data.local.carteira.dao.CarteiraDao
import com.gabriel.crypto_sys.data.local.carteira.model.Carteira
import com.gabriel.crypto_sys.data.remote.firebase.model.Usuario
import com.gabriel.crypto_sys.utils.state.ResourceState
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class FirebaseRepository(
    private val firebaseAuth: FirebaseAuth,
    private val carteiraDao: CarteiraDao
) {
    fun cadastraUsuario(usuario: Usuario): LiveData<ResourceState<Boolean>> {
        val liveData = MutableLiveData<ResourceState<Boolean>>()
        try {
            firebaseAuth.createUserWithEmailAndPassword(usuario.email, usuario.senha)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        salvaCarteira(task)
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

    private fun salvaCarteira(task: Task<AuthResult>) {
        CoroutineScope(IO).launch {
            task.result.user?.uid?.let { carteiraDao.salva(Carteira(id = it)) }
        }
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
                        verifyIfExists(task)
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

    private fun verifyIfExists(task: Task<AuthResult>) {
        CoroutineScope(IO).launch {
            task.result.user?.uid?.let {
                if (!carteiraDao.verifyIfCarteiraExists(carteiraId = it)) {
                    salvaCarteira(task)
                }
            }
        }
    }

    private fun getErrorAuth(exception: java.lang.Exception?): String = when (exception) {
        is FirebaseAuthInvalidUserException,
        is FirebaseAuthInvalidCredentialsException -> "E-mail ou senha incorretos"
        else -> "Erro desconhecido"
    }

    fun estaLogado(): Boolean {
        val userAtual = firebaseAuth.currentUser
        if (userAtual != null) {
            return true
        }
        return false
    }

    fun desloga() {
        firebaseAuth.signOut()
    }
}