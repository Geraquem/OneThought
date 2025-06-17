package com.mmfsin.onethought.presentation.offline.dialogs

import android.app.Dialog
import android.view.LayoutInflater
import com.mmfsin.onethought.base.BaseDialog
import com.mmfsin.onethought.databinding.DialogRoundsBinding

class RoundsDialog(private val listener: IRoundsListener) : BaseDialog<DialogRoundsBinding>() {

    override fun inflateView(inflater: LayoutInflater) = DialogRoundsBinding.inflate(inflater)

    override fun setCustomViewDialog(dialog: Dialog) = centerViewDialog(dialog)

    override fun setUI() {
        isCancelable = false
    }

    override fun setListeners() {
        binding.apply {
            btnFive.setOnClickListener { selectRound(5) }
            btnEight.setOnClickListener { selectRound(8) }
            btnTen.setOnClickListener { selectRound(10) }
            btnFifteen.setOnClickListener { selectRound(15) }
            btnTwenty.setOnClickListener { selectRound(20) }

            btnNone.setOnClickListener {
                listener.goBack()
                dismiss()
            }
        }
    }

    private fun selectRound(rounds: Int) {
        listener.totalRounds(rounds)
        dismiss()
    }

    interface IRoundsListener {
        fun totalRounds(rounds: Int)
        fun goBack()
    }
}