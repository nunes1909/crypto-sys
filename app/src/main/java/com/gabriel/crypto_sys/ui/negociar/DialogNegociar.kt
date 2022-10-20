package com.gabriel.crypto_sys.ui.negociar

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import com.gabriel.crypto_sys.databinding.DialogNegociarBinding

class DialogNegociar : DialogFragment() {

    private val args: DialogNegociarArgs by navArgs()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(context)
        builder.setView(createView())
        return builder.create()
    }

    private fun createView(): View {
        val binding = DialogNegociarBinding.inflate(layoutInflater)
        configuraComponentes(binding)
        return binding.root
    }

    private fun configuraComponentes(binding: DialogNegociarBinding) {
        binding.tvNegociarPrecoAtual.text = args.precoAtual
    }
}
