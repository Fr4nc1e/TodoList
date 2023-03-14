package com.course.todolist.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providePreference(app: Application): SharedPreferences {
        return app.getSharedPreferences(
            "todo",
            Context.MODE_PRIVATE
        )
    }
}
