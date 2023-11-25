package com.best.realindonesianflavors.ui.page.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.best.realindonesianflavors.mydata.FoodEntity
import com.best.realindonesianflavors.mymodel.ViewModelHome
import com.best.realindonesianflavors.ui.component.AvailableView
import com.best.realindonesianflavors.ui.component.EmptyView
import com.best.realindonesianflavors.ui.component.ErrorView
import com.best.realindonesianflavors.ui.component.Loader
import com.best.realindonesianflavors.ui.component.SearchBar
import com.best.realindonesianflavors.utils.ResultState

@Composable
fun HomePage(navController: NavController, scaffoldState: ScaffoldState) {
    val viewModelHome = hiltViewModel<ViewModelHome>()
    val homeUiState by viewModelHome.homeUiState

    viewModelHome.allFood.collectAsState(ResultState.Loading).value.let { resultState ->
        when (resultState) {
            is ResultState.Loading -> Loader()
            is ResultState.Error -> ErrorView()
            is ResultState.Success -> {
                HomeContent(
                    listFood = resultState.data,
                    navController = navController,
                    scaffoldState = scaffoldState,
                    query = homeUiState.query,
                    onQueryChange = viewModelHome::onQueryChange,
                    onUpdateFavoriteFood = viewModelHome::updateFavoriteFood
                )
            }
        }
    }
}

@Composable
fun HomeContent(
    listFood: List<FoodEntity>,
    navController: NavController,
    scaffoldState: ScaffoldState,
    query: String,
    onQueryChange: (String) -> Unit,
    onUpdateFavoriteFood: (id: Int, isFavorite: Boolean) -> Unit
) {
    Column {
        SearchBar(query = query, onQueryChange = onQueryChange)
        when (listFood.isEmpty()) {
            true -> EmptyView()
            false -> AvailableView(listFood, navController, scaffoldState, onUpdateFavoriteFood)

        }
    }
}




