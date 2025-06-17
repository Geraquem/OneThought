package com.mmfsin.onethought.base.dialog

import android.app.Dialog
import android.view.LayoutInflater
import com.mmfsin.onethought.base.BaseDialog
import com.mmfsin.onethought.databinding.DialogErrorBinding

class ErrorDialog(private val goBack: Boolean) : BaseDialog<DialogErrorBinding>() {

    override fun inflateView(inflater: LayoutInflater) = DialogErrorBinding.inflate(inflater)

    override fun setCustomViewDialog(dialog: Dialog) = centerViewDialog(dialog)

    override fun setListeners() {
        binding.btnAccept.setOnClickListener {
            if (goBack) activity?.onBackPressedDispatcher?.onBackPressed()
            dismiss()
        }
    }
}