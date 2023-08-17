package com.tech.wavesfood.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.tech.wavesfood.R
import com.tech.wavesfood.common.PagerSlider
import com.tech.wavesfood.common.TextComponent
import com.tech.wavesfood.common.lato_bold
import com.tech.wavesfood.common.lato_regular
import com.tech.wavesfood.common.yeon_sung_regular
import com.tech.wavesfood.data.ItemMenu
import com.tech.wavesfood.data.itemMenuList
import com.tech.wavesfood.navigation.bottomBarNavigation.BottomBarScreen
import com.tech.wavesfood.navigation.bottomBarNavigation.foodDetail
import com.tech.wavesfood.sharedViewModel.SharedDataWithOtherScreens
import com.tech.wavesfood.ui.theme.GreenColor
import com.tech.wavesfood.ui.theme.WavesFoodTheme
import com.tech.wavesfood.ui.theme.darkWhiteColor

@Composable
fun HomeScreen(navHostController: NavHostController, viewModel: SharedDataWithOtherScreens) {

    Box(
        modifier = Modifier
            .padding(bottom = 50.dp)
            .fillMaxSize()
            .background(darkWhiteColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(darkWhiteColor),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            Header()
            PagerSlider()

            PopularText {
                navHostController.navigate(BottomBarScreen.Search.route)
            }

            LazyColumn {
                items(itemMenuList, key = { it.itemId }) {
                    if (it.itemId <= 5) {
                        PopularEachRow(itemMenu = it, itemOnClick = {
                            viewModel.setData(it.itemImage, it.shortDesc, it.ingredients)
                            navHostController.navigate(foodDetail)
                        }, addToCartOnClick = {
                            viewModel.addToCartList(it.itemId)
                        })
                    }
                }
            }

        }
    }
}

@Composable
fun Header() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextComponent(
            text = stringResource(R.string.explore_your_favorite_food),
            fontSize = 25.sp,
            fontWeight = FontWeight.W600,
            fontFamily = yeon_sung_regular,
            color = Color.Black
        )
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                painter = painterResource(id = R.drawable.bell),
                contentDescription = "",
                tint = GreenColor
            )
        }
    }
}

@Composable
fun PopularText(viewMenuOnClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextComponent(
            text = stringResource(R.string.popular),
            fontSize = 20.sp,
            fontWeight = FontWeight.W600,
            fontFamily = yeon_sung_regular,
            color = Color.Black
        )

        Button(
            onClick = { viewMenuOnClick() },
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 8.dp,
                pressedElevation = 2.dp
            ),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White)
        ) {
            TextComponent(
                text = stringResource(R.string.view_menu),
                fontSize = 14.sp,
                fontWeight = FontWeight.W400,
                fontFamily = lato_bold,
                color = GreenColor
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PopularEachRow(itemMenu: ItemMenu, itemOnClick: () -> Unit,addToCartOnClick:()->Unit) {
    Card(
        onClick = { itemOnClick() },
        modifier = Modifier
            .padding(top = 5.dp, start = 5.dp, end = 5.dp, bottom = 5.dp)
            .width(350.dp)
            .height(100.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp)),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = itemMenu.itemImage),
                contentDescription = "",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .weight(0.33f)
                    .padding(5.dp)
                    .shadow(elevation = 1.dp, shape = RoundedCornerShape(16.dp))
            )
            TextComponent(
                modifier = Modifier.weight(0.33f),
                text = itemMenu.itemName,
                fontSize = 20.sp,
                fontWeight = FontWeight.W400,
                fontFamily = yeon_sung_regular,
                color = Color.Black
            )
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 5.dp, top = 5.dp, bottom = 5.dp)
                    .weight(0.33f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextComponent(
                    text = itemMenu.itemPrice,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W400,
                    fontFamily = yeon_sung_regular,
                    color = GreenColor
                )
                Button(
                    onClick = { addToCartOnClick() },
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 8.dp,
                        pressedElevation = 2.dp
                    ),
                    colors = ButtonDefaults.buttonColors(containerColor = GreenColor),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.width(100.dp).height(40.dp)
                ) {
                    TextComponent(
                        text = stringResource(R.string.add_to_cart),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.W300,
                        fontFamily = lato_bold,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun HomeScreenPreview() {
    WavesFoodTheme {
        val navHostController = rememberNavController()
//        HomeScreen(navHostController = navHostController)
    }
}