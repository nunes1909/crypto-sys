package com.gabriel.crypto_sys.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.gabriel.crypto_sys.R
import com.gabriel.crypto_sys.databinding.ActivityMainBinding
import com.gabriel.crypto_sys.utils.constants.KEY_TOOLS
import com.gabriel.crypto_sys.utils.extensions.hide
import com.gabriel.crypto_sys.utils.extensions.show
import com.gabriel.crypto_sys.utils.preferences.dataStore
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        inicializaView()
        configuraBottomNav()
    }

    private fun configuraBottomNav() = lifecycleScope.launch {
        dataStore.data.collect { preferences ->
            preferences[booleanPreferencesKey(KEY_TOOLS)]?.let {
                if (it) {
                    binding.bottomNavigation.show()
                    supportActionBar?.show()
                } else {
                    binding.bottomNavigation.hide()
                    supportActionBar?.hide()
                }
            }
        }
    }

    private fun inicializaView() {
        navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment

        val navController = navHostFragment.navController

        navController.addOnDestinationChangedListener { navController, navDestination, bundle ->
            title = navDestination.label
        }

        binding.bottomNavigation.apply {
            setupWithNavController(navController)
            setOnNavigationItemReselectedListener { }
        }
    }
}
