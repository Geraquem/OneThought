package com.mmfsin.onethought.presentation.offline

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.mmfsin.onethought.base.BaseFragment
import com.mmfsin.onethought.databinding.FragmentOfflineBinding
import com.mmfsin.onethought.domain.models.Words
import com.mmfsin.onethought.utils.FAST
import com.mmfsin.onethought.utils.MEDIUM
import com.mmfsin.onethought.utils.animateY
import com.mmfsin.onethought.utils.checkNotNulls
import com.mmfsin.onethought.utils.countDown
import com.mmfsin.onethought.utils.showErrorDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OfflineFragment : BaseFragment<FragmentOfflineBinding, OfflineViewModel>() {

    override val viewModel: OfflineViewModel by viewModels()
    private lateinit var mContext: Context

    private var topWords: List<Words>? = null
    private var bottomWords: List<Words>? = null

    private var index = 0

    override fun inflateView(
        inflater: LayoutInflater, container: ViewGroup?
    ) = FragmentOfflineBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getData()
    }

    override fun setUI() {
        showCards(first = true, show = false)
    }

    override fun setListeners() {}

    override fun observe() {
        viewModel.event.observe(this) { event ->
            when (event) {
                is OfflineEvent.Data -> {
                    topWords = event.data.topWords
                    bottomWords = event.data.bottomWords
                    setUpData()
                }

                is OfflineEvent.SWW -> error()
            }
        }
    }

    private fun setUpData() {
        binding.apply {
            checkNotNulls(topWords, bottomWords) { top, bottom ->
                tvTopWord.text = top[index].word
                tvTopReversedWord.text = top[index].word

                tvBottomWord.text = bottom[index].word
                tvBottomReversedWord.text = bottom[index].word

                countDown(500) { showCards(show = true) }
            }
        }
    }

    private fun showCards(first: Boolean = false, show: Boolean) {
        binding.apply {
            val speed = if (first) FAST else MEDIUM
            clCardTop.post {
                val height = if (show) 0f else clCardTop.height
                clCardTop.animateY(-height.toFloat(), speed)
            }
            clCardBottom.post {
                val height = if (show) 0f else clCardBottom.height
                clCardBottom.animateY(height.toFloat(), speed)
            }
        }
    }

    private fun error() = activity?.showErrorDialog(true)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
}