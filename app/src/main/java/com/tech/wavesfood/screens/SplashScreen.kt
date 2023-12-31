package com.tech.wavesfood.screens

import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.google.android.gms.auth.api.identity.Identity
import com.tech.wavesfood.firebase.firebaseAuth.googleSignIn.GoogleAuthUiClient
import com.tech.wavesfood.R
import com.tech.wavesfood.common.TextDesignByAman
import com.tech.wavesfood.common.lato_bold
import com.tech.wavesfood.common.yeon_sung_regular
import com.tech.wavesfood.navigation.bottomNav
import com.tech.wavesfood.navigation.login
import com.tech.wavesfood.ui.theme.GreenColor
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(context : Context,navHostController : NavHostController) {

    val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = context,
            oneTapClient = Identity.getSignInClient(context)
        )
    }
    LaunchedEffect(key1 = Unit, block = {  //navigate to home screen after 2 sec
        delay(1000)
        if (googleAuthUiClient.getSignedInUser() != null) {
            navHostController.navigate(bottomNav){
                launchSingleTop = true
                navHostController.popBackStack()
            }
        }else{
            navHostController.navigate(login)
        }
        Log.d("@@@@", "FoodAdminNavigation: ${googleAuthUiClient.getSignedInUser()}")
    })
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "",
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = stringResource(R.string.waves_of_food), style = TextStyle(
                    fontSize = 50.sp,
                    fontWeight = FontWeight.W400,
                    fontFamily = yeon_sung_regular
                )
            )
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = stringResource(R.string.deliver_favorite_food), style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W400,
                    fontFamily = lato_bold,
                    color = GreenColor
                )
            )
        }
        TextDesignByAman()
    }
}