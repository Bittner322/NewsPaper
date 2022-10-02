package com.example.newspaper.di

private const val DAGGER_APP_COMPONENT_KEY = "MyApplication"

object ComponentStorage {

    private val components: MutableMap<String, Any> = mutableMapOf()

    @Suppress("UNCHECKED_CAST")
    fun <T: Any> provideComponent(key: String, factory: () -> T): T {
        val component = components[key] ?: run {
            val newComponent = factory.invoke()
            components[key] = newComponent
            newComponent
        }
        return component as T
    }

    @Suppress("UNCHECKED_CAST")
    fun <T: Any> provideComponent(key: String): T {
        return components[key] as? T ?: throw IllegalStateException("No component by key: $key")
    }

    fun clearComponent(key: String) {
        components.remove(key)
    }
}

fun <T: Any> ComponentStorage.initRootComponent(factory: () -> T): T {
    return provideComponent(
        key = DAGGER_APP_COMPONENT_KEY,
        factory = factory
    )
}

fun <T: Any> ComponentStorage.provideRootComponent(): T {
    return provideComponent(DAGGER_APP_COMPONENT_KEY)
}