package com.best.realindonesianflavors.mydata

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodDao {
    @Query("SELECT * FROM food")
    fun getAllFood(): Flow<List<FoodEntity>>

    @Query("SELECT * FROM food WHERE isFavorite = 1")
    fun getAllFavoriteFood(): Flow<List<FoodEntity>>

    @Query("SELECT * FROM food WHERE id = :id")
    fun getFood(id: Int): Flow<FoodEntity>

    @Query("SELECT * FROM food WHERE name LIKE '%' || :query || '%'")
    fun searchFood(query: String): Flow<List<FoodEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllFood(foodList: List<FoodEntity>)

    @Query("UPDATE food SET isFavorite = :isFavorite WHERE id = :id")
    suspend fun updateFavoriteFood(id: Int, isFavorite: Boolean)
}