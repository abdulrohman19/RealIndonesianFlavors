package com.best.realindonesianflavors.ui.page.favorite

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.best.realindonesianflavors.mydata.FoodEntity
import com.best.realindonesianflavors.mymodel.ViewModelFavorite
import com.best.realindonesianflavors.ui.component.AvailableView
import com.best.realindonesianflavors.ui.component.EmptyView
import com.best.realindonesianflavors.ui.component.ErrorView
import com.best.realindonesianflavors.ui.component.Loader
import com.best.realindonesianflavors.utils.ResultState

@Composable
fun FavoritePage(navController: NavController, scaffoldState: ScaffoldState) {
    val viewModelFavorite = hiltViewModel<ViewModelFavorite>()

    viewModelFavorite.allFavoriteFood.collectAsState(ResultState.Loading).value.let { resultState ->
        when (resultState) {
            is ResultState.Loading -> Loader()
            is ResultState.Error -> ErrorView()
            is ResultState.Success -> {
                FavoriteContent(
                    listFavoriteFood = resultState.data,
                    navController = navController,
                    scaffoldState = scaffoldState,
                    onUpdateFavoriteFood = viewModelFavorite::updateFavoriteFood
                )
            }
        }
    }
}

@Composable
fun FavoriteContent(
    listFavoriteFood: List<FoodEntity>,
    navController: NavController,
    scaffoldState: ScaffoldState,
    onUpdateFavoriteFood: (id: Int, isFavorite: Boolean) -> Unit
) {
    when (listFavoriteFood.isEmpty()) {
        true -> EmptyView()
        false -> AvailableView(listFavoriteFood, navController, scaffoldState, onUpdateFavoriteFood)
    }
}