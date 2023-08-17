package com.tech.wavesfood.navigation.bottomBarNavigation

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import com.tech.wavesfood.screens.CartScreen
import com.tech.wavesfood.screens.FoodDetailScreen
import com.tech.wavesfood.screens.HistoryScreen
import com.tech.wavesfood.screens.HomeScreen
import com.tech.wavesfood.screens.ProfileScreen
import com.tech.wavesfood.screens.SearchScreen
import com.tech.wavesfood.sharedViewModel.SharedDataWithOtherScreens

@Composable
fun BottomNavGraph( navHostController : NavHostController) {

    val viewModel : SharedDataWithOtherScreens = viewModel()

    NavHost(navController = navHostController , startDestination = BottomBarScreen.Home.route ){

        composable(route = BottomBarScreen.Home.route){
            HomeScreen(navHostController = navHostController,viewModel)
        }
        composable(route = BottomBarScreen.Cart.route){
            CartScreen(navHostController = navHostController,viewModel)
        }
        composable(route = BottomBarScreen.Search.route){
            SearchScreen(navHostController = navHostController,viewModel)
        }
        composable(route = BottomBarScreen.History.route){
            HistoryScreen(navHostController = navHostController)
        }
        composable(route = BottomBarScreen.Profile.route){
            ProfileScreen(navHostController = navHostController)
        }
        composable(foodDetail){
            FoodDetailScreen(navHostController = navHostController,viewModel)
        }
    }
}
const val foodDetail = "foodDetail_screen"
