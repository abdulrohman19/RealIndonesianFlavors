package com.best.realindonesianflavors.mymodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.best.realindonesianflavors.mydata.FoodEntity
import com.best.realindonesianflavors.mydata.IndonesianFoodData
import com.best.realindonesianflavors.myrepo.FoodRepo
import com.best.realindonesianflavors.ui.page.home.HomeUiState
import com.best.realindonesianflavors.utils.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class ViewModelHome  @Inject constructor(private val foodRepo: FoodRepo) : ViewModel() {
    private val _allFood = MutableStateFlow<ResultState<List<FoodEntity>>>(ResultState.Loading)
    val allFood = _allFood.asStateFlow()

    private val _homeUiState = mutableStateOf(HomeUiState())
    val homeUiState: State<HomeUiState> = _homeUiState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            foodRepo.getAllFood().collect { food ->
                when (food.isEmpty()) {
                    true -> foodRepo.insertAllFood(IndonesianFoodData.dummy)
                    else -> _allFood.value = ResultState.Success(food)
                }
            }
        }
    }

    private fun searchFood(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            foodRepo.searchFood(query)
                .catch { _allFood.value = ResultState.Error(it.message.toString()) }
                .collect { _allFood.value = ResultState.Success(it) }
        }
    }

    fun updateFavoriteFood(id: Int, isFavorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            foodRepo.updateFavoriteFood(id, isFavorite)
        }
    }

    fun onQueryChange(query: String) {
        _homeUiState.value = _homeUiState.value.copy(query = query)
        searchFood(query)
    }
}