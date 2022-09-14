package com.example.newspaper.di

import com.example.newspaper.data.database.ArticleDatabase
import com.example.newspaper.data.network.NewsService
import com.example.newspaper.presentation.dialogs.UsernameChangeDialog
import com.example.newspaper.presentation.favorite_articles.FavoriteArticleActivity
import com.example.newspaper.presentation.full_article.FullArticleActivity
import com.example.newspaper.presentation.history.HistoryActivity
import com.example.newspaper.presentation.main_activity.MainActivity
import com.example.newspaper.presentation.news.NewsFragment
import com.example.newspaper.presentation.onboarding_flow.OnboardingFlowActivity
import com.example.newspaper.presentation.onboarding_flow.OnboardingFragmentNumberThree
import com.example.newspaper.presentation.profile.ProfileFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Scope
import javax.inject.Singleton

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ScreenScope

@Component(dependencies = [AppComponent::class])
@ScreenScope
interface FullArticleActivityComponent {

    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent, @BindsInstance url: String) : FullArticleActivityComponent
    }

    fun inject(activity: FullArticleActivity)

}

@Component(dependencies = [AppComponent::class])
@ScreenScope
interface NewsFragmentComponent {

    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): NewsFragmentComponent
    }

    fun inject(fragment: NewsFragment)
}

@Component(dependencies = [AppComponent::class])
@ScreenScope
interface FavoriteArticleActivityComponent {

    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): FavoriteArticleActivityComponent
    }

    fun inject(activity: FavoriteArticleActivity)
}

@Component(dependencies = [AppComponent::class])
@ScreenScope
interface UsernameChangeDialogComponent {

    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): UsernameChangeDialogComponent
    }

    fun inject(fragment: UsernameChangeDialog)
}

@Component(dependencies = [AppComponent::class])
@ScreenScope
interface OnboardingFlowFragmentActivity {

    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): OnboardingFlowFragmentActivity
    }

    fun inject(fragmentActivity: OnboardingFlowActivity)
}

@Component(dependencies = [AppComponent::class])
@ScreenScope
interface HistoryActivityComponent {

    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): HistoryActivityComponent
    }

    fun inject(activity: HistoryActivity)
}

@Component(dependencies = [AppComponent::class])
@ScreenScope
interface ProfileFragmentComponent {

    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): ProfileFragmentComponent
    }

    fun inject(fragment: ProfileFragment)
}

@Component(dependencies = [AppComponent::class])
@ScreenScope
interface OnboardingFragmentNumberThreeComponent {

    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): OnboardingFragmentNumberThreeComponent
    }

    fun inject(fragment: OnboardingFragmentNumberThree)
}

@Component(modules = [
    NetworkModule::class,
    DatabaseModule::class,
])
@Singleton
interface AppComponent {

    val database: ArticleDatabase
    val network: NewsService

}