package com.gabriel.crypto_sys.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gabriel.crypto_sys.databinding.FragmentCadastroUsuarioBinding
import com.gabriel.crypto_sys.ui.base.BaseFragment
import com.gabriel.crypto_sys.ui.viewModel.CadastroViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentCadastro : BaseFragment<FragmentCadastroUsuarioBinding, CadastroViewModel>() {

    override val viewModel: CadastroViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCadastroUsuarioBinding =
        FragmentCadastroUsuarioBinding.inflate(layoutInflater, container, false)
}
