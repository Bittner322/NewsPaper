package com.example.newspaper.presentation.onboarding_flow

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.newspaper.MyApplication
import com.example.newspaper.R
import com.example.newspaper.databinding.FragmentOnboardingNumberTwoBinding
import com.example.newspaper.presentation.main_activity.MainActivity

private const val FIRST_LAUNCH = "firstLaunch"
private const val PREFS = "prefs"

class OnboardingFragmentNumberTwo : Fragment() {

    private var _binding: FragmentOnboardingNumberTwoBinding? = null
    private val binding: FragmentOnboardingNumberTwoBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentOnboardingNumberTwoBinding.inflate(layoutInflater)

        val view = binding.root

        val sharedPref = MyApplication.applicationContext().getSharedPreferences(PREFS, Context.MODE_PRIVATE)

        binding.onboardingStartButton.setOnClickListener {

            with(sharedPref.edit()) {
                putBoolean(FIRST_LAUNCH, false)
                apply()
            }

            val toMainActivityIntent = Intent(activity, MainActivity::class.java)
            activity?.startActivity(toMainActivityIntent)
            activity?.finish()


        }

        return view
    }
}