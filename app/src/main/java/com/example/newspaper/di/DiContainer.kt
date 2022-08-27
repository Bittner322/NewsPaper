package com.example.newspaper.di

object DiContainer {

    private val networkModule = NetworkModule()
    private val databaseModule = DatabaseModule()
    val favoriteArticleModule = FavoriteArticleModule(networkModule, databaseModule)
    val newsFragmentModule = NewsFragmentModule(networkModule, databaseModule)
    val profileFragmentModule = ProfileFragmentModule(databaseModule)
    fun fullArticleModule(url: String) = FullArticleModule(url, databaseModule)
    val historyActivityModule = HistoryActivityModule(networkModule, databaseModule)
    val usernameChangeDialogModule = UsernameChangeDialogModule(databaseModule)
    val categoryFragmentModule = CategoryFragmentModule(networkModule, databaseModule)
    val onboardigFlowModule = OnboardigFlowModule(databaseModule)

}