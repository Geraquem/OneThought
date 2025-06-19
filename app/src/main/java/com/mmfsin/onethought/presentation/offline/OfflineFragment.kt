package com.mmfsin.onethought.presentation.offline

import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.mmfsin.onethought.R
import com.mmfsin.onethought.base.BaseFragment
import com.mmfsin.onethought.databinding.FragmentOfflineBinding
import com.mmfsin.onethought.domain.models.Words
import com.mmfsin.onethought.presentation.offline.dialogs.RoundFinishedDialog
import com.mmfsin.onethought.presentation.offline.dialogs.RoundFinishedDialog.IRoundFinishedListener
import com.mmfsin.onethought.presentation.offline.dialogs.RoundsDialog
import com.mmfsin.onethought.presentation.offline.dialogs.RoundsDialog.IRoundsListener
import com.mmfsin.onethought.utils.FAST
import com.mmfsin.onethought.utils.MEDIUM
import com.mmfsin.onethought.utils.animateY
import com.mmfsin.onethought.utils.checkNotNulls
import com.mmfsin.onethought.utils.countDown
import com.mmfsin.onethought.utils.showAlpha
import com.mmfsin.onethought.utils.showErrorDialog
import com.mmfsin.onethought.utils.showFragmentDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OfflineFragment : BaseFragment<FragmentOfflineBinding, OfflineViewModel>(), IRoundsListener,
    IRoundFinishedListener {

    override val viewModel: OfflineViewModel by viewModels()
    private lateinit var mContext: Context

    private var topWords: List<Words>? = null
    private var bottomWords: List<Words>? = null

    private var index = 0
    private var points = 0
    private var totalRounds: Int? = null

    override fun inflateView(
        inflater: LayoutInflater, container: ViewGroup?
    ) = FragmentOfflineBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.showFragmentDialog(RoundsDialog(this@OfflineFragment))
    }

    override fun totalRounds(rounds: Int) {
        totalRounds = rounds
        updateRounds()
        updatePoints()
        showBottomData(show = true)

        /** starting game */
        viewModel.getData()
    }

    override fun setUI() {
        binding.apply {
            countdown.tvCount.visibility = View.INVISIBLE
            clBottom.tvPercentConnection.text = getString(R.string.rounds_connection_zero)
            showCards(first = true, show = false)
            showBottomData(show = false)
        }
    }

    override fun setListeners() {
        binding.apply {}
    }

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
                updateRounds()

                countDown(500) {
                    tvTopWord.text = top[index].word
                    tvTopReversedWord.text = top[index].word

                    tvBottomWord.text = bottom[index].word
                    tvBottomReversedWord.text = bottom[index].word

                    showCards(show = true)
                    startCountdown()
                }
            }
        }
    }

    private fun startCountdown() {
//        val totalMillis = 5000L
        val totalMillis = 500L
        val intervalMillis = 1000L
        val count = binding.countdown.tvCount

        object : CountDownTimer(totalMillis, intervalMillis) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsRemaining = (millisUntilFinished + intervalMillis - 1) / intervalMillis
                count.visibility = View.VISIBLE
                val seconds = secondsRemaining.toString()
                count.text = seconds
            }

            override fun onFinish() {
                count.text = getString(R.string.zero)
                count.visibility = View.INVISIBLE
                activity?.showFragmentDialog(
                    RoundFinishedDialog(
                        this@OfflineFragment,
                        isFinalRound = (index + 1) == totalRounds
                    )
                )
            }
        }.start()
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

    private fun showBottomData(show: Boolean) {
        binding.apply {
            val speed = if (show) MEDIUM else FAST
            val container = clBottom.container
            container.post { container.showAlpha(show, speed) }
        }
    }

    private fun updateRounds() {
        totalRounds?.let { rounds ->
            val result = getString(R.string.rounds_total, (index + 1).toString(), rounds.toString())
            binding.clBottom.tvRoundsCount.text = result
            updateMentalConnection()
        }
    }

    private fun updatePoints() {
        val points = points.toString()
        binding.clBottom.tvPoints.text = points
    }

    private fun updateMentalConnection() {
        if (index != 0) {
            val percent = (points * 100) / index
            binding.clBottom.tvPercentConnection.text =
                getString(R.string.rounds_connection_percent, percent.toString())
        }
    }

    override fun roundFinished(successful: Boolean) {
        if (successful) {
            points++
            updatePoints()
        }
        updateRounds()
        nextRound()
    }

    private fun nextRound() {
        showCards(show = false)

        totalRounds?.let { rounds ->
            index++
            if (index >= rounds) {
                Toast.makeText(requireContext(), "FINISH", Toast.LENGTH_SHORT).show()
            } else setUpData()

        } ?: run { error() }
    }

    override fun goBack() {
        activity?.onBackPressedDispatcher?.onBackPressed()
    }

    private fun error() = activity?.showErrorDialog(true)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
}