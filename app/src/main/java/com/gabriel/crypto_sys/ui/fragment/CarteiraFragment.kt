package com.gabriel.crypto_sys.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gabriel.crypto_sys.databinding.FragmentCarteiraBinding
import com.gabriel.crypto_sys.ui.base.BaseFragment
import com.gabriel.crypto_sys.ui.viewModel.CarteiraViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CarteiraFragment : BaseFragment<FragmentCarteiraBinding, CarteiraViewModel>() {

    override val viewModel: CarteiraViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCarteiraBinding =
        FragmentCarteiraBinding.inflate(layoutInflater, container, false)
}
