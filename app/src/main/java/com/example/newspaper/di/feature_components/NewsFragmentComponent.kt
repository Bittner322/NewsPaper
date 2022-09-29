package com.example.newspaper.di.feature_components

import com.example.newspaper.di.AppComponent
import com.example.newspaper.di.scopes.ScreenScope
import com.example.newspaper.presentation.news.NewsFragment
import dagger.Component

@Component(dependencies = [AppComponent::class])
@ScreenScope
interface NewsFragmentComponent {

    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): NewsFragmentComponent
    }

    fun inject(fragment: NewsFragment)
}