package com.tech.wavesfood.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.tech.wavesfood.R
import com.tech.wavesfood.common.ButtonComponent
import com.tech.wavesfood.common.TextComponent
import com.tech.wavesfood.common.lato_regular
import com.tech.wavesfood.common.yeon_sung_regular
import com.tech.wavesfood.navigation.bottomBarNavigation.BottomBarScreen
import com.tech.wavesfood.sharedViewModel.SharedDataWithOtherScreens
import com.tech.wavesfood.ui.theme.GreenColor
import com.tech.wavesfood.ui.theme.darkWhiteColor

@Composable
fun FoodDetailScreen(navHostController: NavHostController,viewModel:SharedDataWithOtherScreens) {

    Box(
        modifier = Modifier
            .padding(bottom = 50.dp)
            .fillMaxSize()
            .background(darkWhiteColor)
    ) {
        Column(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp, top = 5.dp)
                .fillMaxSize()
                .background(darkWhiteColor),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            HeadingWithIcon(stringResource(R.string.food_detail)){
                navHostController.navigateUp()
            }
            Spacer(modifier = Modifier.height(10.dp))
            Image(
                painter = painterResource(id = viewModel.itemImage.value),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .shadow(elevation = 1.dp, shape = RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(10.dp))
            ShortDesc(viewModel)
            Spacer(modifier = Modifier.height(5.dp))
            Ingredients(viewModel)

            Spacer(modifier = Modifier.height(5.dp))
            ButtonComponent(
                text = stringResource(id = R.string.add_to_cart),
                backgroundColor = GreenColor,
                foregroundColor = Color.White
            ) {
                navHostController.navigate(BottomBarScreen.Cart.route)
            }
        }
    }
}

@Composable
fun HeadingWithIcon(headingName : String,onBackClick:()->Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            painter = painterResource(id = R.drawable.arrow_left),
            contentDescription = "",
            tint = Color.Unspecified,
            modifier = Modifier
                .weight(0.1f)
                .clickable {
                    onBackClick()
                }
        )

        Text(text = headingName, style = TextStyle(
            fontSize = 26.sp,
            fontWeight = FontWeight.W600,
            fontFamily = yeon_sung_regular,
            color = Color.Black,
            textAlign = TextAlign.Center
        ) ,modifier = Modifier.weight(0.9f))
    }
}

@Composable
fun ShortDesc(viewModel: SharedDataWithOtherScreens) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        TextComponent(
            text = stringResource(id = R.string.short_description) ,
            fontSize = 20.sp,
            fontWeight = FontWeight.W400,
            fontFamily = yeon_sung_regular,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(5.dp))
        TextComponent(
            text = viewModel.shortDesc.value,
            fontSize = 16.sp,
            fontWeight = FontWeight.W200,
            fontFamily = lato_regular,
            color = Color.Black
        )
    }
}

@Composable
fun Ingredients(viewModel: SharedDataWithOtherScreens) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        TextComponent(
            text = stringResource(R.string.ingredients),
            fontSize = 20.sp,
            fontWeight = FontWeight.W400,
            fontFamily = yeon_sung_regular,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(5.dp))
        TextComponent(
            text = viewModel.ingredients.value,
            fontSize = 16.sp,
            fontWeight = FontWeight.W200,
            fontFamily = lato_regular,
            color = Color.Black
        )
    }
}