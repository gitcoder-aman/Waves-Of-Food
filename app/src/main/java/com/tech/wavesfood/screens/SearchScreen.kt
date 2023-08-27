package com.tech.wavesfood.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.tech.wavesfood.R
import com.tech.wavesfood.common.TextComponent
import com.tech.wavesfood.common.lato_regular
import com.tech.wavesfood.common.yeon_sung_regular
import com.tech.wavesfood.data.itemMenuList
import com.tech.wavesfood.navigation.bottomBarNavigation.BottomBarScreen
import com.tech.wavesfood.navigation.bottomBarNavigation.foodDetail
import com.tech.wavesfood.sharedViewModel.SharedDataWithOtherScreens
import com.tech.wavesfood.ui.theme.GreenColor
import com.tech.wavesfood.ui.theme.darkWhiteColor

@Composable
fun SearchScreen(navHostController: NavHostController, viewModel: SharedDataWithOtherScreens) {

    var searchText by remember {
        mutableStateOf("")
    }
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
            Spacer(modifier = Modifier.height(5.dp))
            TextFieldLayout(searchText) {
                searchText = it
            }
            Spacer(modifier = Modifier.height(10.dp))

            TextComponent(
                text = stringResource(R.string.menu),
                fontSize = 24.sp,
                fontWeight = FontWeight.W400,
                fontFamily = yeon_sung_regular,
                color = Color.Black
            )

            LazyColumn {
                items(itemMenuList, key = { it.itemId }) {
                    PopularEachRow(itemMenu = it, itemOnClick = {
                        viewModel.setData(it.itemId,it.itemImage, it.shortDesc, it.ingredients,it.itemPrice)
                        navHostController.navigate(foodDetail)
                    }, addToCartOnClick = { string ->
                        if (string == "add")
                            viewModel.addToCartList(it.itemId)
                        else
                            navHostController.navigate(BottomBarScreen.Cart.route)
                    })
                }
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldLayout(searchText: String, onValueChange: (String) -> Unit) {

    TextField(
        value = searchText,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        placeholder = {
            TextComponent(
                text = stringResource(R.string.what_do_you_want_to_order),
                fontSize = 14.sp,
                fontWeight = FontWeight.W100,
                fontFamily = lato_regular,
                color = Color.Gray
            )
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.search),
                contentDescription = "",
                tint = Color.Unspecified
            )
        },
        keyboardActions = KeyboardActions(KeyboardActions.Default.onSearch),
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black,
            cursorColor = GreenColor,
            selectionColors = TextSelectionColors(
                handleColor = GreenColor,
                backgroundColor = GreenColor
            ), focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            disabledTextColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )

}