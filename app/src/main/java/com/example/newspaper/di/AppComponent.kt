package com.example.newspaper.di

import com.example.newspaper.presentation.news.NewsFragment
import dagger.Component

@Component(modules = [
    CategoryFragmentModule::class,
    FavoriteArticleModule::class,
    FullArticleModule::class,
    HistoryActivityModule::class,
    NewsFragmentModule::class,
    OnboardingFlowModule::class,
    ProfileFragmentModule::class,
    UsernameChangeDialogModule::class
])
interface AppComponent {

    fun inject(fragment: NewsFragment)

}