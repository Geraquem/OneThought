package com.mmfsin.onethought.presentation.offline.dialogs

import android.app.Dialog
import android.view.LayoutInflater
import com.mmfsin.onethought.base.BaseDialog
import com.mmfsin.onethought.databinding.DialogRoundFinishedBinding

class RoundFinishedDialog(private val listener: IRoundFinishedListener) :
    BaseDialog<DialogRoundFinishedBinding>() {

    override fun inflateView(inflater: LayoutInflater) =
        DialogRoundFinishedBinding.inflate(inflater)

    override fun setCustomViewDialog(dialog: Dialog) = centerViewDialog(dialog)

    override fun setUI() {
        isCancelable = false
    }

    override fun setListeners() {
        binding.apply {
            btnYes.setOnClickListener { result(successful = true) }
            btnNo.setOnClickListener { result(successful = false) }
        }
    }

    private fun result(successful: Boolean) {
        listener.roundFinished(successful)
        dismiss()
    }

    interface IRoundFinishedListener {
        fun roundFinished(successful: Boolean)
    }
}