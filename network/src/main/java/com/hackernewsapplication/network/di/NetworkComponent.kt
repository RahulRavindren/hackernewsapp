package com.hackernewsapplication.network.di

import dagger.Subcomponent

@Subcomponent(modules = arrayOf(NetworkModule::class))
interface NetworkComponent {

    @Subcomponent.Builder
    interface Builder {

    }
}