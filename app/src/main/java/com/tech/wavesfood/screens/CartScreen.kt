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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.tech.wavesfood.R
import com.tech.wavesfood.common.yeon_sung_regular
import com.tech.wavesfood.data.ItemMenu
import com.tech.wavesfood.data.itemMenuList
import com.tech.wavesfood.navigation.bottomBarNavigation.foodDetail
import com.tech.wavesfood.sharedViewModel.SharedDataWithOtherScreens
import com.tech.wavesfood.ui.theme.GreenColor
import com.tech.wavesfood.ui.theme.darkWhiteColor

@Composable
fun CartScreen(navHostController: NavHostController,viewModel : SharedDataWithOtherScreens) {

    Box(
        modifier = Modifier
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
            Spacer(modifier = Modifier.height(5.dp))
            HeadingWithIcon(headingName = stringResource(R.string.cart)){
                navHostController.navigateUp()
            }
            Spacer(modifier = Modifier.height(10.dp))
            LazyColumn {
               items(viewModel.cartList, key = {it}){
                   CartEachRow(itemMenu = itemMenuList[it], itemOnClick = {
                       viewModel.setData(itemMenuList[it].itemImage,itemMenuList[it].shortDesc,itemMenuList[it].ingredients)
                       navHostController.navigate(foodDetail)
                   }) {

                   }
               }
            }
            
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartEachRow(itemMenu: ItemMenu, itemOnClick:()->Unit, onDelete:()->Unit) {

    Card(onClick = {itemOnClick() },
        modifier = Modifier
            .padding(top = 5.dp, start = 5.dp, end = 5.dp, bottom = 5.dp)
            .width(350.dp)
            .height(100.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp)),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
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

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 5.dp, top = 5.dp, bottom = 5.dp)
                    .weight(0.33f),
                verticalArrangement = Arrangement.spacedBy(8.dp,Alignment.CenterVertically),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = itemMenu.itemName, style = TextStyle(
                        fontSize = 22.sp,
                        fontWeight = FontWeight.W600,
                        fontFamily = yeon_sung_regular
                    ), maxLines = 1
                )
                Text(
                    text = itemMenu.itemPrice, style = TextStyle(
                        fontSize = 22.sp,
                        fontWeight = FontWeight.W400,
                        fontFamily = yeon_sung_regular,
                        color = GreenColor,
                    ), maxLines = 1
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 5.dp, top = 5.dp, bottom = 5.dp)
                    .weight(0.33f), verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {

                }
                IconButton(onClick = {  //delete functionality data from firebase
                    onDelete()
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.trash),
                        contentDescription = "",
                        tint = GreenColor,
                        modifier = Modifier.padding(bottom = 5.dp)
                    )
                }
            }
        }
    }
}