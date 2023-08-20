package com.tech.wavesfood.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import com.tech.wavesfood.common.ButtonComponent
import com.tech.wavesfood.common.TextComponent
import com.tech.wavesfood.common.lato_bold
import com.tech.wavesfood.common.yeon_sung_regular
import com.tech.wavesfood.data.CartMenu
import com.tech.wavesfood.data.ItemMenu
import com.tech.wavesfood.data.cartItemRemove
import com.tech.wavesfood.data.cartList
import com.tech.wavesfood.data.itemMenuList
import com.tech.wavesfood.navigation.bottomBarNavigation.BottomBarScreen
import com.tech.wavesfood.navigation.bottomBarNavigation.foodDetail
import com.tech.wavesfood.sharedViewModel.SharedDataWithOtherScreens
import com.tech.wavesfood.ui.theme.GreenColor
import com.tech.wavesfood.ui.theme.darkWhiteColor

@Composable
fun CartScreen(navHostController: NavHostController, viewModel: SharedDataWithOtherScreens) {

    Box(
        modifier = Modifier
            .padding(bottom = 50.dp)
            .fillMaxSize()
            .background(darkWhiteColor),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(darkWhiteColor),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(5.dp))
            HeadingWithIcon(headingName = stringResource(R.string.cart)) {
                navHostController.navigateUp()
            }
            Spacer(modifier = Modifier.height(10.dp))
            LazyColumn {
                items(viewModel.cartList.toList(), key = { it }) { itemIndex ->
                    CartEachRow(
                        itemIndex = itemIndex,
                        itemMenu = itemMenuList[itemIndex],
                        itemOnClick = {
                            viewModel.setData(
                                itemMenuList[itemIndex].itemImage,
                                itemMenuList[itemIndex].shortDesc,
                                itemMenuList[itemIndex].ingredients
                            )
                            navHostController.navigate(foodDetail)
                        },
                        onDelete = {
                            viewModel.removeItem(index = itemIndex)
                            cartItemRemove(itemIndex)
                            ///here an error of index problem when delete item
                        })
                }
            }
        }
        ButtonComponent(
            text = stringResource(R.string.proceed),
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = Color.White,
            foregroundColor = GreenColor,
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 8.dp,
                pressedElevation = 4.dp
            )
        ) {
            navHostController.navigate(BottomBarScreen.Delivery.route)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartEachRow(itemIndex: Int, itemMenu: ItemMenu, itemOnClick: () -> Unit, onDelete: () -> Unit) {

    var count by rememberSaveable {
        mutableIntStateOf(1)
    }
    Card(
        onClick = { itemOnClick() },
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
                verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
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
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(5.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_minus),
                        contentDescription = "minus-button",
                        modifier = Modifier
                            .weight(0.33f)
                            .clickable {
                                if (count > 1)
                                    count--
                            }, tint = Color.Unspecified
                    )
                    TextComponent(
                        text = count.toString(),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W200,
                        fontFamily = lato_bold,
                        color = Color.Black,

                        )
                    Icon(
                        painter = painterResource(id = R.drawable.icon_plus),
                        contentDescription = "plus-button",
                        modifier = Modifier
                            .weight(0.33f)
                            .clickable {
                                count++
                            }, tint = Color.Unspecified
                    )
                    val cartMenu = CartMenu(itemCount = count, itemIndex = itemIndex)
                    cartList(cartMenu)
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