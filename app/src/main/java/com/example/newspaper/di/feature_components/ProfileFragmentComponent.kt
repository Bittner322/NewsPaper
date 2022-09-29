package com.example.newspaper.di.feature_components

import com.example.newspaper.di.AppComponent
import com.example.newspaper.di.scopes.ScreenScope
import com.example.newspaper.presentation.profile.ProfileFragment
import dagger.Component

@Component(dependencies = [AppComponent::class])
@ScreenScope
interface ProfileFragmentComponent {

    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): ProfileFragmentComponent
    }

    fun inject(fragment: ProfileFragment)
}