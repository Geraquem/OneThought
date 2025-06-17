package com.mmfsin.onethought.presentation.offline

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.mmfsin.onethought.base.BaseFragment
import com.mmfsin.onethought.databinding.FragmentMenuBinding
import com.mmfsin.onethought.utils.showErrorDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OfflineFragment : BaseFragment<FragmentMenuBinding, OfflineViewModel>() {

    override val viewModel: OfflineViewModel by viewModels()
    private lateinit var mContext: Context

    override fun inflateView(
        inflater: LayoutInflater, container: ViewGroup?
    ) = FragmentMenuBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        viewModel.getDefaultRoutines()
    }

    override fun setUI() {
        binding.apply {}
    }

    override fun setListeners() {}

    override fun observe() {
        viewModel.event.observe(this) { event ->
            when (event) {
                is OfflineEvent.Data -> setUpData()
                is OfflineEvent.SWW -> error()
            }
        }
    }

    private fun setUpData() {

    }

    private fun error() = activity?.showErrorDialog(true)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
}