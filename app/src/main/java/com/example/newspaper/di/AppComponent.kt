package com.example.newspaper.di

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



}