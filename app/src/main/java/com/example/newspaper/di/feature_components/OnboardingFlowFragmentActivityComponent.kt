package com.example.newspaper.di.feature_components

import com.example.newspaper.di.AppComponent
import com.example.newspaper.di.scopes.ScreenScope
import com.example.newspaper.presentation.onboarding_flow.OnboardingFlowActivity
import dagger.Component

@Component(dependencies = [AppComponent::class])
@ScreenScope
interface OnboardingFlowFragmentActivityComponent {

    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): OnboardingFlowFragmentActivityComponent
    }

    fun inject(fragmentActivity: OnboardingFlowActivity)
}