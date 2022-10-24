package com.gabriel.crypto_sys.utils.di

import androidx.room.Room
import com.gabriel.crypto_sys.data.local.database.CryptoSysDataBase
import com.gabriel.crypto_sys.data.remote.retrofit.CryptoRetrofit
import com.gabriel.crypto_sys.data.remote.coin.service.CryptoService
import com.gabriel.crypto_sys.repository.coin.CoinsRepository
import com.gabriel.crypto_sys.repository.datalhes.DetalhesRepository
import com.gabriel.crypto_sys.repository.carteira.CarteiraRepository
import com.gabriel.crypto_sys.repository.firebase.FirebaseRepository
import com.gabriel.crypto_sys.repository.transacao.TransacaoRepository
import com.gabriel.crypto_sys.ui.cadastro.CadastroViewModel
import com.gabriel.crypto_sys.ui.carteira.CarteiraViewModel
import com.gabriel.crypto_sys.ui.coins.CoinsViewModel
import com.gabriel.crypto_sys.ui.detalhes.DetalhesViewModel
import com.gabriel.crypto_sys.ui.login.LoginViewModel
import com.gabriel.crypto_sys.ui.transacao.TransacaoViewModel
import com.gabriel.crypto_sys.utils.constants.DB_NAME
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val localModules = module {
    single { get<CryptoSysDataBase>().getCoinDao() }
    single { get<CryptoSysDataBase>().getCarteiraDao() }
    single { get<CryptoSysDataBase>().getTransacaoDao() }
    single {
        Room.databaseBuilder(
            get(),
            CryptoSysDataBase::class.java,
            DB_NAME
        ).fallbackToDestructiveMigration().build()
    }
}

val remoteModules = module {
    single<OkHttpClient> { OkHttpClient() }
    single<Retrofit> { CryptoRetrofit().getRetrofit(get()) }
    single<CryptoService> { CryptoRetrofit().getCryptoService(get()) }
}

val repositorysModules = module {
    single<FirebaseRepository> { FirebaseRepository(FirebaseAuth.getInstance(), get()) }
    single<CarteiraRepository> { CarteiraRepository(get()) }
    single<CoinsRepository> { CoinsRepository(get()) }
    single<DetalhesRepository> { DetalhesRepository(get()) }
    single<TransacaoRepository> { TransacaoRepository(get()) }
}

val viewModelModules = module {
    viewModel<LoginViewModel> { LoginViewModel(get()) }
    viewModel<CadastroViewModel> { CadastroViewModel(get()) }
    viewModel<CoinsViewModel> { CoinsViewModel(get()) }
    viewModel<DetalhesViewModel> { DetalhesViewModel(get()) }
    viewModel<CarteiraViewModel> { CarteiraViewModel(get(), get()) }
    viewModel<TransacaoViewModel> { TransacaoViewModel(get(), get(), get()) }
}

val firebaseModule = module {
    single<FirebaseAuth> { Firebase.auth }
}