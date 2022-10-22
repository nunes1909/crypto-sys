package com.gabriel.crypto_sys.ui.carteira

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import com.gabriel.crypto_sys.data.local.carteira.model.Carteira
import com.gabriel.crypto_sys.databinding.DialogCarteiraBinding
import com.gabriel.crypto_sys.utils.extensions.show
import com.gabriel.crypto_sys.utils.extensions.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class CarteiraDialog : DialogFragment() {

    private val viewModel: CarteiraViewModel by viewModel()
    private val args: CarteiraDialogArgs by navArgs()
    private lateinit var binding: DialogCarteiraBinding
    private lateinit var carteira: Carteira

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(context)
        builder.setView(createView())
        return builder.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        carteira = args.carteira
    }

    private fun createView(): View {
        binding = DialogCarteiraBinding.inflate(layoutInflater)
        configuraComponentes()
        return binding.root
    }

    private fun configuraComponentes() {
        defineBotoesDeAcao()
        configuraSaldo()
        configuraAcao()
    }

    private fun defineBotoesDeAcao() {
        if (args.depositar) {
            binding.btnDepositar.show()
        } else {
            binding.btnSacar.show()
        }
    }

    private fun configuraSaldo() {
        val saldo = "R$ ${args.carteira.saldo ?: 0}"
        binding.tvSaldo.text = saldo
    }

    private fun configuraAcao() = with(binding) {
        btnDepositar.setOnClickListener {
            if (etValor.toString().trim().isBlank()) {
                toast("Para depositar, preencha um valor.")
            } else {
                viewModel.salvaCarteira(
                    carteira.apply { this.saldo += etValor.text.toString().toInt() }
                )
                dismiss()
            }

        }
    }
}