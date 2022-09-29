package com.example.newspaper.di.feature_components

import com.example.newspaper.di.AppComponent
import com.example.newspaper.di.scopes.ScreenScope
import com.example.newspaper.presentation.dialogs.UsernameChangeDialog
import dagger.Component

@Component(dependencies = [AppComponent::class])
@ScreenScope
interface UsernameChangeDialogComponent {

    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): UsernameChangeDialogComponent
    }

    fun inject(fragment: UsernameChangeDialog)
}