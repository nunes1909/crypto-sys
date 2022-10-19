package com.gabriel.crypto_sys.ui.activity

import android.app.Application
import com.gabriel.crypto_sys.di.firebaseModule
import com.gabriel.crypto_sys.di.repositorysModules
import com.gabriel.crypto_sys.di.viewModelModules
import com.google.firebase.FirebaseApp
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CryptoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        startKoin {
            androidContext(this@CryptoApplication)
            modules(
                listOf(
                    firebaseModule,
                    repositorysModules,
                    viewModelModules
                )
            )
        }
    }
}
