package com.mmfsin.onethought.presentation.activities

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.mmfsin.onethought.R
import com.mmfsin.onethought.base.bedrock.BedRockActivity
import com.mmfsin.onethought.databinding.ActivityMainBinding
import com.mmfsin.onethought.utils.BEDROCK_BOOLEAN_ARGS
import com.mmfsin.onethought.utils.BEDROCK_PARCELABLE_ARGS
import com.mmfsin.onethought.utils.BEDROCK_STR_ARGS
import com.mmfsin.onethought.utils.ROOT_ACTIVITY_NAV_GRAPH
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        changeStatusBar()
        observe()

    //        setAds()
    }

    private fun changeStatusBar() {
        WindowCompat.setDecorFitsSystemWindows(window, true)
        window.statusBarColor = ContextCompat.getColor(this, R.color.white)
        val controller = WindowInsetsControllerCompat(window, window.decorView)
        controller.isAppearanceLightStatusBars = true
    }

    private fun observe() {
        viewModel.event.observe(this) { event ->
            when (event) {
                is MainEvent.GetData -> {
                }

                is MainEvent.SWW -> {} //showErrorDialog() { finish() }
            }
        }
    }

    fun openBedRockActivity(
        navGraph: Int,
        strArgs: String? = null,
        booleanArgs: Boolean? = null,
        parcelable: Any? = null
    ) {
        val intent = Intent(this, BedRockActivity::class.java)
        strArgs?.let { intent.putExtra(BEDROCK_STR_ARGS, strArgs) }
        booleanArgs?.let { intent.putExtra(BEDROCK_BOOLEAN_ARGS, booleanArgs) }
        parcelable?.let { intent.putExtra(BEDROCK_PARCELABLE_ARGS, parcelable as Parcelable) }
        intent.putExtra(ROOT_ACTIVITY_NAV_GRAPH, navGraph)
        startActivity(intent)
    }
}