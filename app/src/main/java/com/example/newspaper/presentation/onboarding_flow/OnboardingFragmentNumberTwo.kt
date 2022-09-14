package com.example.newspaper.presentation.onboarding_flow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.newspaper.databinding.FragmentOnboardingNumberTwoBinding

class OnboardingFragmentNumberTwo : Fragment() {

    private var _binding: FragmentOnboardingNumberTwoBinding? = null
    private val binding: FragmentOnboardingNumberTwoBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentOnboardingNumberTwoBinding.inflate(layoutInflater)

        return binding.root
    }
}