package com.example.newspaper.di.feature_components

import com.example.newspaper.di.AppComponent
import com.example.newspaper.di.scopes.ScreenScope
import com.example.newspaper.presentation.history.HistoryActivity
import dagger.Component

@Component(dependencies = [AppComponent::class])
@ScreenScope
interface HistoryActivityComponent {

    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): HistoryActivityComponent
    }

    fun inject(activity: HistoryActivity)
}