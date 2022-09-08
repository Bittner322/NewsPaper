package com.example.newspaper.di

import com.example.newspaper.presentation.dialogs.UsernameChangeDialog
import com.example.newspaper.presentation.news.NewsFragment
import com.example.newspaper.presentation.onboarding_flow.OnboardingFragmentNumberThree
import com.example.newspaper.presentation.profile.ProfileFragment
import dagger.Component
import javax.inject.Singleton

@Component(modules = [
    FavoriteArticleModule::class,
    FullArticleModule::class,
    HistoryActivityModule::class,
    NewsFragmentModule::class,
    OnboardingFlowModule::class,
    ProfileFragmentModule::class,
    UsernameChangeDialogModule::class
])
@Singleton
interface AppComponent {

    fun inject(fragment: NewsFragment)
    fun inject(fragment: OnboardingFragmentNumberThree)
    fun inject(fragment: ProfileFragment)
    fun inject(fragment: UsernameChangeDialog)



}