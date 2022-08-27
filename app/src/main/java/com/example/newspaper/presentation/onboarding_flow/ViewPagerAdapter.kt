package com.example.newspaper.presentation.onboarding_flow

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

private const val NUM_PAGES = 3

class ViewPagerAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount() = NUM_PAGES

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> OnboardingFragmentNumberOne()
            1 -> OnboardingFragmentNumberTwo()
            2 -> OnboardingFragmentNumberThree()
            else -> {
                OnboardingFragmentNumberTwo()
            }
        }
    }

}