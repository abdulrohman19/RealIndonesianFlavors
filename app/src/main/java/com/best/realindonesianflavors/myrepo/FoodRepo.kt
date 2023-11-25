package com.best.realindonesianflavors.myrepo

import javax.inject.Singleton
import javax.inject.Inject
import com.best.realindonesianflavors.mydata.FoodDao
import com.best.realindonesianflavors.mydata.FoodEntity

@Singleton

class FoodRepo @Inject constructor(private val foodDao: FoodDao) {
    fun getAllFood() = foodDao.getAllFood()
    fun getAllFavoriteFood() = foodDao.getAllFavoriteFood()
    fun getFood(id: Int) = foodDao.getFood(id)
    fun searchFood(query: String) = foodDao.searchFood(query)
    suspend fun insertAllFood(food: List<FoodEntity>) = foodDao.insertAllFood(food)
    suspend fun updateFavoriteFood(id: Int, isFavorite: Boolean) = foodDao.updateFavoriteFood(id, isFavorite)
}