package com.example.eventsapp.commons.di

import com.example.eventsapp.events.api.EventsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ActivityComponent::class)
class RetrofitModule {

    private fun create(): Retrofit = Retrofit.Builder()
        .baseUrl(URL_BASE)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun providerEventsList(): EventsApiService =
        create().create(EventsApiService::class.java)

    companion object {
        private const val URL_BASE = "https://5f5a8f24d44d640016169133.mockapi.io/api/"
    }
}