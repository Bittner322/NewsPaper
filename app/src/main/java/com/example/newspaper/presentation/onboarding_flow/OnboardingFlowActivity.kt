package com.example.newspaper.presentation.onboarding_flow

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import com.example.newspaper.MyApplication
import com.example.newspaper.databinding.ActivityOnboardingFlowBinding
import com.example.newspaper.di.ComponentStorage
import com.example.newspaper.di.feature_components.DaggerOnboardingFlowFragmentActivityComponent
import com.example.newspaper.di.provideRootComponent
import com.example.newspaper.presentation.main_activity.MainActivity
import javax.inject.Inject

private const val FIRST_LAUNCH = "firstLaunch"
private const val PREFS = "prefs"

class OnboardingFlowActivity : FragmentActivity() {

    private var _binding: ActivityOnboardingFlowBinding? = null
    private val binding: ActivityOnboardingFlowBinding
        get() = _binding!!

    @Inject
    lateinit var viewModelFactory: OnboardingFlowViewModelFactory

    private val daggerComponentKey = "OnboardingFlowActivity"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ComponentStorage.provideComponent(daggerComponentKey) {
            DaggerOnboardingFlowFragmentActivityComponent.factory().create(
                appComponent = ComponentStorage.provideRootComponent()
            )
        }.inject(this)

        _binding = ActivityOnboardingFlowBinding.inflate(layoutInflater)
        val view = binding.root

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

    override fun onDestroy() {
        if (!isChangingConfigurations) {
            ComponentStorage.clearComponent(daggerComponentKey)
        }
        super.onDestroy()
    }
}