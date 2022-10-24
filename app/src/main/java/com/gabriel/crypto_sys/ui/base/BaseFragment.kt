package com.gabriel.crypto_sys.ui.base

import android.os.Bundle
import android.view.*
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.gabriel.crypto_sys.NavGraphDirections
import com.gabriel.crypto_sys.R
import com.gabriel.crypto_sys.ui.login.LoginViewModel
import com.gabriel.crypto_sys.utils.constants.KEY_TOOLS
import com.gabriel.crypto_sys.utils.preferences.dataStore
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

abstract class BaseFragment<viewBinding : ViewBinding, viewModel : ViewModel> : Fragment() {

    private var _binding: viewBinding? = null
    protected val binding get() = _binding!!

    protected abstract val viewModel: viewModel

    private val loginViewModel: LoginViewModel by viewModel()
    protected val controller by lazy { findNavController() }
    private val firebaseAuth: FirebaseAuth by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        verificaEstaLogado()
        configuraVisibilityBottomNav()
    }

    private fun configuraVisibilityBottomNav() = lifecycleScope.launch {
        requireContext().dataStore.edit { preferences ->
            preferences[booleanPreferencesKey(KEY_TOOLS)] = true
        }
    }

    private fun verificaEstaLogado() {
        if (!loginViewModel.estaLogado()) {
            vaiParaLogin()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_acbar, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.itemLogout) {
            loginViewModel.desloga()
            vaiParaLogin()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun vaiParaLogin() {
        val action = NavGraphDirections.acaoGlobalParaLogin()
        controller.navigate(action)
    }

    fun getUserAtual(): FirebaseUser? {
        return firebaseAuth.currentUser
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = getViewBinding(inflater, container)
        return binding.root
    }

    abstract fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): viewBinding?

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
