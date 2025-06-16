package com.mmfsin.onethought.presentation.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.mmfsin.onethought.databinding.ActivityMainBinding
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
        observe()
        viewModel.getData()
//        setAds()
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
}