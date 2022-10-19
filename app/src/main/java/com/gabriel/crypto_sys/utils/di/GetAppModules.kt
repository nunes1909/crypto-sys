package com.gabriel.crypto_sys.utils.di

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.gabriel.crypto_sys.data.local.database.CryptoSysDataBase
import com.gabriel.crypto_sys.data.remote.retrofit.CryptoRetrofit
import com.gabriel.crypto_sys.data.remote.service.CryptoService
import com.gabriel.crypto_sys.repository.CoinsRepository
import com.gabriel.crypto_sys.repository.FirebaseRepository
import com.gabriel.crypto_sys.ui.cadastro.CadastroViewModel
import com.gabriel.crypto_sys.ui.coins.CoinsViewModel
import com.gabriel.crypto_sys.ui.login.LoginViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val localModules = module {
    single { get<CryptoSysDataBase>().getCoinDao() }
    single {
        Room.databaseBuilder(
            get(),
            CryptoSysDataBase::class.java,
            "coins.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val remoteModules = module {
    single<OkHttpClient> { OkHttpClient() }
    single<Retrofit> { CryptoRetrofit().getRetrofit(get()) }
    single<CryptoService> { CryptoRetrofit().getCryptoService(get()) }
}

val repositorysModules = module {
    single<FirebaseRepository> { FirebaseRepository(FirebaseAuth.getInstance()) }
    single<CoinsRepository> { CoinsRepository(get()) }
}

val viewModelModules = module {
    viewModel<LoginViewModel> { LoginViewModel(get()) }
    viewModel<CadastroViewModel> { CadastroViewModel(get()) }
    viewModel<CoinsViewModel> { CoinsViewModel(get()) }
}

val firebaseModule = module {
    single<FirebaseAuth> { Firebase.auth }
}