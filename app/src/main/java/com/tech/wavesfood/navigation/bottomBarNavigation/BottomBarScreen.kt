package com.tech.wavesfood.navigation.bottomBarNavigation

import com.tech.wavesfood.R

sealed class BottomBarScreen(
    val route:String,
    val title : String,
    val icon : Int,
){
    //for home
    object Home : BottomBarScreen(
        route = "home",
        title = "Home",
        icon = R.drawable.home,
    )
    //for cart
    object Cart : BottomBarScreen(
        route = "cart",
        title = "Cart",
        icon = R.drawable.cart,
    )
    //for search
    object Search : BottomBarScreen(
        route = "search",
        title = "Search",
        icon = R.drawable.search,
    )
    //for history
    object Delivery : BottomBarScreen(
        route = "delivery",
        title = "Delivery",
        icon = R.drawable.truck,
    )
    //for profile
    object Profile : BottomBarScreen(
        route = "profile",
        title = "Profile",
        icon = R.drawable.usernew,
    )
}