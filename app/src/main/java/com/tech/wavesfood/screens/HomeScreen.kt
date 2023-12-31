package com.tech.wavesfood.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.tech.wavesfood.R
import com.tech.wavesfood.common.PagerSlider
import com.tech.wavesfood.common.TextComponent
import com.tech.wavesfood.common.lato_bold
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
                            viewModel.setData(it.itemId,it.itemImage, it.shortDesc, it.ingredients,it.itemPrice)
                            navHostController.navigate(foodDetail)
                        }, addToCartOnClick = { string ->

                            if (string == "add")
                                viewModel.addToCartList(itemMenuList.indexOf(it))  //store index of item
                            else
                                navHostController.navigate(BottomBarScreen.Cart.route)
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
            .padding(start = 10.dp, end = 10.dp, bottom = 5.dp),
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
                defaultElevation = 4.dp,
                pressedElevation = 2.dp
            ),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            modifier = Modifier
                .height(30.dp)
                .width(80.dp)
        ) {
            TextComponent(
                text = stringResource(R.string.view_menu),
                fontSize = 14.sp,
                fontWeight = FontWeight.W400,
                fontFamily = lato_bold,
                color = GreenColor,
                textAlign = TextAlign.Center
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PopularEachRow(
    itemMenu: ItemMenu,
    itemOnClick: () -> Unit,
    addToCartOnClick: (String) -> Unit
) {

    var cart by remember { mutableIntStateOf(R.drawable.add_cart) }
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
                    text = itemMenu.itemPrice.toString(),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W400,
                    fontFamily = yeon_sung_regular,
                    color = GreenColor
                )
                Button(
                    onClick = {
                        if (cart == R.drawable.add_cart) {
                            cart = R.drawable.go_cart
                            addToCartOnClick("add")
                        } else {
                            addToCartOnClick("go")
                        }
                    },
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 8.dp,
                        pressedElevation = 2.dp
                    ),
                    colors = ButtonDefaults.buttonColors(containerColor = GreenColor),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .width(80.dp)
                        .height(30.dp)
                ) {
                    Icon(
                        painter = painterResource(id = cart),
                        contentDescription = "",
                        tint = Color.White,
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