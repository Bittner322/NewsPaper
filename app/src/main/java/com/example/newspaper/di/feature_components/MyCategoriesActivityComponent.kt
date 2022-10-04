package com.example.newspaper.di.feature_components

import com.example.newspaper.di.AppComponent
import com.example.newspaper.di.scopes.ScreenScope
import com.example.newspaper.presentation.categories_activity.MyCategoriesActivity
import dagger.Component

@Component(dependencies = [AppComponent::class])
@ScreenScope
interface MyCategoriesActivityComponent {

    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): MyCategoriesActivityComponent
    }

    fun inject(activity: MyCategoriesActivity)
}