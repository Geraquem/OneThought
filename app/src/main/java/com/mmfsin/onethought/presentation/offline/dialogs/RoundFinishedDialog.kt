package com.mmfsin.onethought.presentation.offline.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.VibrationEffect.DEFAULT_AMPLITUDE
import android.os.Vibrator
import android.os.VibratorManager
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.isVisible
import com.mmfsin.onethought.base.BaseDialog
import com.mmfsin.onethought.databinding.DialogRoundFinishedBinding
import com.mmfsin.onethought.utils.countDown

class RoundFinishedDialog(
    private val listener: IRoundFinishedListener,
    val isFinalRound: Boolean
) : BaseDialog<DialogRoundFinishedBinding>() {

    override fun inflateView(inflater: LayoutInflater) =
        DialogRoundFinishedBinding.inflate(inflater)

    override fun setCustomViewDialog(dialog: Dialog) = centerViewDialog(dialog)

    override fun setUI() {
        isCancelable = false
        vibrate()
        binding.apply {

            val aa = if (isFinalRound) "yes" else "no"
            tvAnswer.text = aa

            llButtons.isVisible = false
            tvNextRound.visibility = View.INVISIBLE
//            countDown(2500) {
            countDown(500) {
                llButtons.isVisible = true
            }
        }
    }

    override fun setListeners() {
        binding.apply {
            btnYes.setOnClickListener { result(successful = true) }
            btnNo.setOnClickListener { result(successful = false) }
        }
    }

    private fun vibrate() {
        val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val vibratorManager =
                context?.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
            vibratorManager.defaultVibrator
        } else {
            @Suppress("DEPRECATION")
            context?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        }

        if (!vibrator.hasVibrator()) return

        val vibrationEffect = VibrationEffect.createOneShot(2000, DEFAULT_AMPLITUDE)
        vibrator.vibrate(vibrationEffect)
    }

    private fun result(successful: Boolean) {
        binding.apply {
            llAnswer.visibility = View.INVISIBLE
            llButtons.visibility = View.INVISIBLE

            if (isFinalRound) exitDialog(successful)
            else {
                tvNextRound.visibility = View.VISIBLE
//                countDown(1250) { exitDialog(successful) }
                countDown(250) { exitDialog(successful) }
            }
        }
    }

    private fun exitDialog(successful: Boolean) {
        listener.roundFinished(successful)
        dismiss()
    }

    interface IRoundFinishedListener {
        fun roundFinished(successful: Boolean)
    }
}