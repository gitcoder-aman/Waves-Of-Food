package com.tech.wavesfood.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.tech.wavesfood.R
import com.tech.wavesfood.common.ButtonComponent
import com.tech.wavesfood.common.TextComponent
import com.tech.wavesfood.common.lato_bold
import com.tech.wavesfood.common.lato_regular
import com.tech.wavesfood.common.yeon_sung_regular
import com.tech.wavesfood.navigation.bottomBarNavigation.BottomBarScreen
import com.tech.wavesfood.ui.theme.GreenColor
import com.tech.wavesfood.ui.theme.darkWhiteColor

@Composable
fun DeliveryScreen(navHostController: NavHostController) {

    var customerName by rememberSaveable {
        mutableStateOf("")
    }
    var customerAddress by rememberSaveable {
        mutableStateOf("")
    }
    var customerPhone by rememberSaveable {
        mutableStateOf("")
    }
    val scrollState = rememberScrollState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(darkWhiteColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
                .verticalScroll(scrollState)
                .background(darkWhiteColor),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            HeadingWithIcon(headingName = stringResource(id = R.string.your_order), onBackClick = {
                navHostController.navigateUp()
            })
//            Log.d(
//                "@@@@",
//                "CartEachRow: ${"itemIndex " + cartList[0].itemIndex} ${"itemCount " + cartList[0].itemCount} ${"Size " + cartList.size}"
//            )
//            Log.d(
//                "@@@@",
//                "CartEachRow: ${"itemIndex " + cartList[1].itemIndex} ${"itemCount " + cartList[1].itemCount} ${"Size " + cartList.size}"
//            )
//            Log.d(
//                "@@@@",
//                "CartEachRow: ${"itemIndex " + cartList[2].itemIndex} ${"itemCount " + cartList[2].itemCount} ${"Size " + cartList.size}"
//            )
            Spacer(modifier = Modifier.height(10.dp))
            TextFieldWithLeadingText(leadingText = stringResource(id = R.string.name),
                placeholderText = stringResource(R.string.lorem_name),
                text = customerName,
                onValueChange = {
                    customerName = it
                })
            Spacer(modifier = Modifier.height(10.dp))

            TextFieldWithLeadingText(leadingText = stringResource(R.string.address),
                placeholderText = stringResource(R.string.lorem_address),
                text = customerAddress,
                onValueChange = {
                    customerAddress = it
                })
            Spacer(modifier = Modifier.height(10.dp))

            TextFieldWithLeadingText(leadingText = stringResource(id = R.string.phone),
                placeholderText = "0010100101",
                text = customerPhone,
                onValueChange = {
                    customerPhone = it
                })

            Spacer(modifier = Modifier.height(10.dp))
            TextFieldWithTrailing(placeholderText = stringResource(R.string.payment_method), trailingIcon = R.drawable.cashondelivery)
            Spacer(modifier = Modifier.height(10.dp))
            TextFieldWithTrailing(
                placeholderText = stringResource(R.string.total_amount), trailingText = "$35")

            ButtonComponent(
                text = stringResource(R.string.place_my_order),
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = Color.White,
                foregroundColor = GreenColor,
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 8.dp,
                    pressedElevation = 4.dp
                )
            ) {
                //any function perform when order is completed
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldWithLeadingText(
    leadingText: String, placeholderText: String, text: String, onValueChange: (String) -> Unit
) {

    TextField(
        value = text,
        onValueChange = onValueChange,
        modifier = Modifier
            .shadow(2.dp, RoundedCornerShape(16.dp))
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        leadingIcon = {
            TextComponent(
                text = leadingText,
                fontSize = 16.sp,
                fontWeight = FontWeight.W400,
                fontFamily = yeon_sung_regular,
                color = Color.Black,
                maxLine = 4
            )
        },
        placeholder = {
            TextComponent(
                text = placeholderText,
                fontSize = 12.sp,
                fontWeight = FontWeight.W200,
                fontFamily = lato_regular,
                color = Color.Gray
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black,
            containerColor = Color.White,
            cursorColor = GreenColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            selectionColors = TextSelectionColors(
                handleColor = GreenColor, backgroundColor = GreenColor
            )
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldWithTrailing(placeholderText: String,@DrawableRes trailingIcon: Int = 0, trailingText: String = "") {

    TextField(
        value = "",
        onValueChange = {},
        modifier = Modifier
            .shadow(2.dp, RoundedCornerShape(16.dp))
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        trailingIcon = {
            if(trailingIcon != 0){
                Icon(
                    painter = painterResource(id = trailingIcon),
                    contentDescription = "",
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .width(100.dp)
                        .height(50.dp)
                        .padding(5.dp)
                )
            }else{
                TextComponent(
                    text = trailingText,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W400,
                    fontFamily = lato_bold,
                    color = GreenColor,
                    modifier = Modifier.padding(end = 20.dp)
                )
            }

        }, readOnly = true,
        enabled = false,
        placeholder = {
            TextComponent(
                text = placeholderText,
                fontSize = 12.sp,
                fontWeight = FontWeight.W400,
                fontFamily = lato_regular,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black,
            containerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}