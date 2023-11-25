package com.best.realindonesianflavors.myrepo

import com.best.realindonesianflavors.mydata.FoodDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ComponentRepo {
    @Provides
    @ViewModelScoped
    fun provideRepository(foodDao: FoodDao) = FoodRepo(foodDao)
}