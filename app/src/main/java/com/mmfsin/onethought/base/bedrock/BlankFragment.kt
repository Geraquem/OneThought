package com.mmfsin.onethought.base.bedrock

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mmfsin.onethought.base.BaseFragmentNoVM
import com.mmfsin.onethought.databinding.FragmentBlankBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BlankFragment : BaseFragmentNoVM<FragmentBlankBinding>() {

    override fun inflateView(
        inflater: LayoutInflater, container: ViewGroup?
    ) = FragmentBlankBinding.inflate(inflater, container, false)
}