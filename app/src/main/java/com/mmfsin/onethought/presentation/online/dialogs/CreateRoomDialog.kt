package com.mmfsin.onethought.presentation.online.dialogs

import android.app.Dialog
import android.view.LayoutInflater
import com.mmfsin.onethought.base.BaseDialog
import com.mmfsin.onethought.databinding.DialogCreateRoomBinding

class CreateRoomDialog(
    private val roomName: (name: String) -> Unit
) : BaseDialog<DialogCreateRoomBinding>() {

    override fun inflateView(inflater: LayoutInflater) = DialogCreateRoomBinding.inflate(inflater)

    override fun setCustomViewDialog(dialog: Dialog) = centerViewDialog(dialog)

    override fun setUI() {
        isCancelable = true
    }

    override fun setListeners() {
        binding.apply {
            btnAccept.setOnClickListener {
                roomName(etRoomName.text.toString())
                dismiss()
            }
        }
    }
}