package com.example.newspaper.di.feature_components

import com.example.newspaper.di.AppComponent
import com.example.newspaper.di.scopes.ScreenScope
import com.example.newspaper.presentation.onboarding_flow.OnboardingFragmentNumberThree
import dagger.Component

@Component(dependencies = [AppComponent::class])
@ScreenScope
interface OnboardingFragmentNumberThreeComponent {

    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): OnboardingFragmentNumberThreeComponent
    }

    fun inject(fragment: OnboardingFragmentNumberThree)
}