package com.gabriel.crypto_sys.ui.carteira

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import com.gabriel.crypto_sys.databinding.DialogCarteiraBinding
import com.gabriel.crypto_sys.utils.extensions.show

class CarteiraDialog : DialogFragment() {

    private val args: CarteiraDialogArgs by navArgs()
    private lateinit var binding: DialogCarteiraBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(context)
        builder.setView(createView())
        return builder.create()
    }

    private fun createView(): View {
        binding = DialogCarteiraBinding.inflate(layoutInflater)
        configuraComponentes()
        return binding.root
    }

    private fun configuraComponentes() {
        configuraBotoesDeAcao()
        configuraSaldo()
    }

    private fun configuraSaldo() {
        val saldo = args.carteira.saldo?.let { binding.tvSaldo.text = it.toString() } ?: "0"
        binding.tvSaldo.text = "R$ $saldo"
    }

    private fun configuraBotoesDeAcao() {
        if (args.depositar) {
            binding.btnDepositar.show()
        } else {
            binding.btnSacar.show()
        }
    }
}