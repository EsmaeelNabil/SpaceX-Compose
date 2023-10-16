package com.thermondo.common.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val dispatcher: ThermondoDispatchers)

enum class ThermondoDispatchers {
    Default,
    IO,
    Main
}

