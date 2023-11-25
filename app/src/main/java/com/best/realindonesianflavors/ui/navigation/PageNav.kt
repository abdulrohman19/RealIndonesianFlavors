package com.best.realindonesianflavors.ui.navigation

sealed class PageNav(val route: String) {
    object Home : PageNav("home")
    object Favorite : PageNav("favorite")
    object Profile : PageNav("profile")
    object Detail : PageNav("home/{foodId}") {
        fun createRoute(foodId: Int) = "home/$foodId"
    }
}