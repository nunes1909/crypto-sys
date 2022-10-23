package com.gabriel.crypto_sys.ui.carteira

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import com.gabriel.crypto_sys.data.local.carteira.model.Carteira
import com.gabriel.crypto_sys.databinding.DialogCarteiraBinding
import com.gabriel.crypto_sys.ui.carteira.validaCarteira.ValidaDeposito
import com.gabriel.crypto_sys.ui.carteira.validaCarteira.ValidaSaque
import com.gabriel.crypto_sys.utils.extensions.show
import com.gabriel.crypto_sys.utils.extensions.toast
import com.gabriel.crypto_sys.utils.state.ResourceState
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
        defineSaldoNaTela()
        defineBotoesNaTela()
        configuraDeposito()
        configuraSaque()
        return binding.root
    }

    private fun defineSaldoNaTela() {
        val saldo = "R$ ${args.carteira.saldo}"
        binding.tvSaldo.text = saldo
    }

    private fun defineBotoesNaTela() {
        if (args.depositar) {
            binding.btnDepositar.show()
        } else {
            binding.btnSacar.show()
        }
    }

    private fun configuraDeposito() = with(binding) {
        btnDepositar.setOnClickListener {
            val deposito = etValor.text.toString().takeIf { it.trim().isNotEmpty() } ?: "0"

            val resource = ValidaDeposito().regraCalculo(
                valor = deposito.toInt(),
                carteira = carteira
            )

            when (resource) {
                is ResourceState.Success -> {
                    viewModel.salvaCarteira(resource.data!!)
                    dismiss()
                }
                else -> {
                    etValor.error = resource.message
                }
            }

        }
    }

    private fun configuraSaque() = with(binding) {
        btnSacar.setOnClickListener {
            val saque = etValor.text.toString().takeIf { it.trim().isNotEmpty() } ?: "0"

            val resource = ValidaSaque().regraCalculo(
                valor = saque.toInt(),
                carteira = carteira
            )

            when (resource) {
                is ResourceState.Success -> {
                    viewModel.salvaCarteira(resource.data!!)
                    dismiss()
                }
                else -> {
                    etValor.error = resource.message
                }
            }

        }
    }

}
