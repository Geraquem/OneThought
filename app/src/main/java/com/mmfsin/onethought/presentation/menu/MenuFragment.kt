package com.mmfsin.onethought.presentation.menu

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.mmfsin.onethought.R
import com.mmfsin.onethought.base.BaseFragment
import com.mmfsin.onethought.databinding.FragmentMenuBinding
import com.mmfsin.onethought.presentation.activities.MainActivity
import com.mmfsin.onethought.presentation.online.dialogs.CreateRoomDialog
import com.mmfsin.onethought.utils.showFragmentDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuFragment : BaseFragment<FragmentMenuBinding, MenuViewModel>() {

    override val viewModel: MenuViewModel by viewModels()
    private lateinit var mContext: Context

    override fun inflateView(
        inflater: LayoutInflater, container: ViewGroup?
    ) = FragmentMenuBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        navigateToOffline()
    }

    override fun setUI() {
        binding.apply {}
    }

    override fun setListeners() {
        binding.apply {
            btnCreateRoom.setOnClickListener {
                activity?.showFragmentDialog(CreateRoomDialog { viewModel.createRoom(it) })
            }

            btnOffline.setOnClickListener { navigateToOffline() }
        }
    }

    private fun navigateToOffline() {
        if (activity is MainActivity) {
            (activity as MainActivity).openBedRockActivity(navGraph = R.navigation.nav_graph_offline)
        }
    }

    override fun observe() {
        viewModel.event.observe(this) { event ->
            when (event) {
                is MenuEvent.RoomCreated -> {
                    println("-*-*--*-*--*-*--*-*--*-*--*-*--*-*--*-*--*-*--*-*--*-*--*-*--*-*--*-*--*-*--*-*--*-*--*-*--*-*--*-*-")
                    println("room createdddd")
                    println("-*-*--*-*--*-*--*-*--*-*--*-*--*-*--*-*--*-*--*-*--*-*--*-*--*-*--*-*--*-*--*-*--*-*--*-*--*-*--*-*-")
                }

                is MenuEvent.SWW -> error()
            }
        }
    }

    private fun error() {}
//    activity?.showErrorDialog()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
}