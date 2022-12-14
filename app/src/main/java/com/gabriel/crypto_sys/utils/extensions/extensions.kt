package com.gabriel.crypto_sys.utils.extensions

import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun Fragment.toast(mensagem: String, duracao: Int = Toast.LENGTH_LONG) {
    Toast.makeText(requireContext(), mensagem, duracao).show()
}

fun View.snackBar(mensagem: String, duracao: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(this, mensagem, duracao).show()
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun String.limitValue(limit: Int, ellipsize: Boolean = false): String {
    val ellEnd = if (ellipsize) "..." else ""
    if (this.length > limit) {
        val first = 0
        return "${this.substring(first, limit)}$ellEnd"
    }
    return this
}
