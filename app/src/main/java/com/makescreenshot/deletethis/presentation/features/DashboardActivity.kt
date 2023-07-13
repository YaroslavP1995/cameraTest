package com.makescreenshot.deletethis.presentation.features

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.makescreenshot.deletethis.R
import com.makescreenshot.deletethis.databinding.ActivityDashboardBinding
import com.makescreenshot.deletethis.presentation.utils.findNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardActivity : AppCompatActivity() {

    private val binding: ActivityDashboardBinding by lazy {
        ActivityDashboardBinding.inflate(layoutInflater)
    }
    private val navController by lazy { findNavController(R.id.hostFragment) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        navController.setGraph(R.navigation.dashboard_nav)
    }

}