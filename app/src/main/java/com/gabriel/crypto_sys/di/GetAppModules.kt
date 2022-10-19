package com.gabriel.crypto_sys.di

import com.gabriel.crypto_sys.repository.FirebaseRepository
import com.gabriel.crypto_sys.ui.viewModel.CadastroViewModel
import com.gabriel.crypto_sys.ui.viewModel.LoginViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repositorysModules = module {
    single<FirebaseRepository> { FirebaseRepository(FirebaseAuth.getInstance()) }
}

val viewModelModules = module {
    viewModel<LoginViewModel> { LoginViewModel(get()) }
    viewModel<CadastroViewModel> { CadastroViewModel(get()) }
}

val firebaseModule = module {
    single<FirebaseAuth> { Firebase.auth }
}