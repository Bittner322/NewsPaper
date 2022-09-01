package com.example.newspaper.presentation.onboarding_flow

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import com.example.newspaper.MyApplication
import com.example.newspaper.databinding.ActivityOnboardingFlowBinding
import com.example.newspaper.di.DiContainer
import com.example.newspaper.presentation.main_activity.MainActivity

private const val FIRST_LAUNCH = "firstLaunch"
private const val PREFS = "prefs"

class OnboardingFlowActivity : FragmentActivity() {

    private var _binding: ActivityOnboardingFlowBinding? = null
    private val binding: ActivityOnboardingFlowBinding
        get() = _binding!!

    private val viewModel: OnboardingFlowViewModel by viewModels { DiContainer.onboardingFlowModule.viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        _binding = ActivityOnboardingFlowBinding.inflate(layoutInflater)
        val view = binding.root

        lifecycleScope.launchWhenStarted {
            viewModel.setNewUser()
        }

        val sharedPref = MyApplication.applicationContext().getSharedPreferences(PREFS, Context.MODE_PRIVATE)

        val isFirstLaunch = sharedPref.getBoolean(FIRST_LAUNCH, true)
        if(!isFirstLaunch) {
            val toMainActivityIntent = Intent(this, MainActivity::class.java)
            startActivity(toMainActivityIntent)
            finish()
        }

        setContentView(view)

        binding.onboardingViewPager.adapter = ViewPagerAdapter(this)
        binding.wormDotsIndicator.attachTo(binding.onboardingViewPager)

    }
}