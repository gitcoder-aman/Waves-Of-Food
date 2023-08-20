package com.tech.wavesfood.navigation.bottomBarNavigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.tech.wavesfood.common.TextComponent
import com.tech.wavesfood.common.lato_regular
import com.tech.wavesfood.ui.theme.GreenColor
import com.tech.wavesfood.ui.theme.darkWhiteColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigation() {
    val navController = rememberNavController()

    Scaffold(bottomBar = {
        BottomBar(navHostController = navController)
    }) {
        Modifier.padding(it)
        BottomNavGraph(navHostController = navController)
    }
}

@Composable
fun BottomBar(navHostController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Cart,
        BottomBarScreen.Search,
        BottomBarScreen.Delivery,
        BottomBarScreen.Profile,
    )
//    val navStackBackEntry by navHostController.currentBackStackEntryAsState()
//    val currentDestination = navStackBackEntry?.destination

    Row(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
//                currentDestination = currentDestination!!,
                navHostController = navHostController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
//    currentDestination: NavDestination,
    navHostController: NavHostController
) {
//    val selected = currentDestination.hierarchy.any { it.route == screen.route }   //boolean store

    Box(modifier = Modifier
        .height(50.dp)
        .clip(CircleShape)
        .background(darkWhiteColor)
        .clickable {
            navHostController.navigate(screen.route) {
                popUpTo(navHostController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }) {
        Column(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp, top = 10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Icon(
                painter = painterResource(id = screen.icon),
                contentDescription = "icon",
                tint = GreenColor
            )
            AnimatedVisibility(visible = true) {
                TextComponent(
                    text = screen.title,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.W400,
                    fontFamily = lato_regular,
                    color = Color.Black
                )
            }

        }

    }
}