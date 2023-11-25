package com.best.realindonesianflavors.mydata

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDependency {
    @Provides
    @Singleton
    fun provideDatabase(application: Application) = Room
        .databaseBuilder(application, FoodDatabase::class.java, "realindonesianflavors.db")
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    fun provideDao(database: FoodDatabase) = database.foodDao()
}
