package com.example.newspaper

import android.app.Application
import android.content.Context
import com.example.newspaper.data.database.ArticleDatabase
import com.example.newspaper.di.AppComponent
import com.example.newspaper.di.DaggerAppComponent

class MyApplication: Application() {

    companion object {

        private lateinit var instance: MyApplication
        lateinit var appComponent: AppComponent
            private set

        private val components: MutableMap<String, Any> = mutableMapOf()

        fun applicationContext(): Context {
            return instance.applicationContext
        }

        @Suppress("UNCHECKED_CAST")
        fun <T: Any> provideComponent(key: String, factory: () -> T): T {
            val component = components[key] ?: run {
                val newComponent = factory.invoke()
                components[key] = newComponent
                newComponent
            }
            return component as T
        }

        fun clearComponent(key: String) {
            components.remove(key)
        }

    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.create()
        ArticleDatabase.initDatabase(this)
    }
}