package com.mmfsin.onethought.utils

import androidx.fragment.app.FragmentActivity
import com.mmfsin.onethought.base.dialog.ErrorDialog

fun FragmentActivity.showErrorDialog(goBack: Boolean = false) {
    val dialog = ErrorDialog(goBack)
    this.let { dialog.show(it.supportFragmentManager, "") }
}