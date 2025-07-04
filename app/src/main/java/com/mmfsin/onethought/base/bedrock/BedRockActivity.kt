package com.mmfsin.onethought.base.bedrock

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import com.mmfsin.onethought.R
import com.mmfsin.onethought.databinding.ActivityBedrockBinding
import com.mmfsin.onethought.utils.ROOT_ACTIVITY_NAV_GRAPH
import com.mmfsin.onethought.utils.showErrorDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BedRockActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBedrockBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBedrockBinding.inflate(layoutInflater)
        setContentView(binding.root)

        changeStatusBar()
        setUpToolbar()
        setUpNavGraph()
    }

    private fun changeStatusBar() {
        WindowCompat.setDecorFitsSystemWindows(window, true)
        window.statusBarColor = ContextCompat.getColor(this, R.color.white)
        val controller = WindowInsetsControllerCompat(window, window.decorView)
        controller.isAppearanceLightStatusBars = true
    }

    private fun setUpNavGraph() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.br_fragment_container) as NavHostFragment
        val navController = navHostFragment.navController
        val navGraph = intent.getIntExtra(ROOT_ACTIVITY_NAV_GRAPH, -1)
        navController.apply { if (navGraph != -1) setGraph(navGraph) else error() }
    }

    fun setUpToolbar(title: String? = "") {
        binding.toolbar.apply {
            ivBack.setOnClickListener { onBackPressedDispatcher.onBackPressed() }
            tvTitle.text = title
        }
    }

    fun rightIconToolbar(isVisible: Boolean, icon: Int? = null, action: () -> Unit = {}) {
        binding.toolbar.apply {
            ivRightIcon.isVisible = isVisible
            icon?.let { ivRightIcon.setImageResource(icon) }
            ivRightIcon.setOnClickListener { action() }
        }
    }

    private fun error() = showErrorDialog()
}