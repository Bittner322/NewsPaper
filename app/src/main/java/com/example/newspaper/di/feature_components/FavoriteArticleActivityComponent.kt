package com.example.newspaper.di.feature_components

import com.example.newspaper.di.AppComponent
import com.example.newspaper.di.scopes.ScreenScope
import com.example.newspaper.presentation.favorite_articles.FavoriteArticleActivity
import dagger.Component

@Component(dependencies = [AppComponent::class])
@ScreenScope
interface FavoriteArticleActivityComponent {

    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): FavoriteArticleActivityComponent
    }

    fun inject(activity: FavoriteArticleActivity)
}