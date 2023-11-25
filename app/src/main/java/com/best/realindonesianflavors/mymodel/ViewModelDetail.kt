package com.best.realindonesianflavors.mymodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.best.realindonesianflavors.mydata.FoodEntity
import com.best.realindonesianflavors.myrepo.FoodRepo
import com.best.realindonesianflavors.utils.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class ViewModelDetail @Inject constructor(private val foodRepo: FoodRepo) : ViewModel() {
    private val _food = MutableStateFlow<ResultState<FoodEntity>>(ResultState.Loading)
    val food = _food.asStateFlow()

    fun getFood(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            foodRepo.getFood(id)
                .catch { _food.value = ResultState.Error(it.message.toString()) }
                .collect { _food.value = ResultState.Success(it) }
        }
    }

    fun updateFavoriteFood(id: Int, isFavorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            foodRepo.updateFavoriteFood(id, isFavorite)
        }
    }
}