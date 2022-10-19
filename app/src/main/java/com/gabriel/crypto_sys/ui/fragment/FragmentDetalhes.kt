package com.gabriel.crypto_sys.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gabriel.crypto_sys.databinding.FragmentDetalhesBinding
import com.gabriel.crypto_sys.ui.base.BaseFragment
import com.gabriel.crypto_sys.ui.viewModel.DetalhesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentDetalhes : BaseFragment<FragmentDetalhesBinding, DetalhesViewModel>() {

    override val viewModel: DetalhesViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetalhesBinding =
        FragmentDetalhesBinding.inflate(layoutInflater, container, false)
}
