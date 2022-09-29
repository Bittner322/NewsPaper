package com.example.newspaper.di.feature_components

import com.example.newspaper.di.AppComponent
import com.example.newspaper.di.scopes.ScreenScope
import com.example.newspaper.presentation.full_article.FullArticleActivity
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [AppComponent::class])
@ScreenScope
interface FullArticleActivityComponent {

    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent, @BindsInstance url: String) : FullArticleActivityComponent
    }

    fun inject(activity: FullArticleActivity)

}