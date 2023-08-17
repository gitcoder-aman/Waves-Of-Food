package com.tech.wavesfood.navigation

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.auth.api.identity.Identity
import com.tech.wavesfood.firebase.firebaseAuth.googleSignIn.GoogleAuthUiClient
import com.tech.wavesfood.firebase.firebaseAuth.googleSignIn.UserData
import com.tech.wavesfood.firebase.firebaseAuth.ui.AuthViewModel
import com.tech.wavesfood.navigation.bottomBarNavigation.BottomNavigation
import com.tech.wavesfood.screens.FoodDetailScreen
import com.tech.wavesfood.screens.HomeScreen
import com.tech.wavesfood.screens.LoginScreen
import com.tech.wavesfood.screens.SignupScreen
import com.tech.wavesfood.screens.SplashScreen
import kotlinx.coroutines.launch

@Composable
fun WavesFoodNavigation(context : Context) {
    val navHostController = rememberNavController()

    val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = context,
            oneTapClient = Identity.getSignInClient(context)
        )
    }
    NavHost(navController = navHostController, startDestination = splash){

        composable(login){

            val authViewModel: AuthViewModel = hiltViewModel()
            val state by authViewModel.state.collectAsStateWithLifecycle()

            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.StartIntentSenderForResult(),
                onResult = { result ->
                    if (result.resultCode == Activity.RESULT_OK) {
                        it.lifecycleScope.launch {
                            val signInResult = googleAuthUiClient.signInWithIntent(
                                intent = result.data ?: return@launch
                            )
                            authViewModel.onSignInResult(signInResult)
                        }
                    }
                }
            )
            LaunchedEffect(key1 = state.isSignInSuccessful) {
                if (state.isSignInSuccessful) {  //get all the the of google signIn
                    navHostController.navigate(bottomNav)

                    val user: UserData? =
                        googleAuthUiClient.getSignedInUser()   //all data get from signedInUser
                    Log.d("@@@@", "User uid: ${user?.userId}")
                    Log.d("@@@@", "User name: ${user?.userName}")
                    Log.d("@@@@", "User photo url: ${user?.profilePictureUrl}")

                    Toast.makeText(context, "Sign in Successful", Toast.LENGTH_LONG).show()
                }
            }

            LoginScreen(state = state, onSignInClick = {
                it.lifecycleScope.launch {
                    val signInIntentSender = googleAuthUiClient.signIn()
                    launcher.launch(
                        IntentSenderRequest
                            .Builder(signInIntentSender ?: return@launch)
                            .build()
                    )
                }
            }, navHostController = navHostController)
        }
        composable(splash){
            SplashScreen(context = context, navHostController = navHostController)
        }
        composable(signup){
            SignupScreen(navHostController = navHostController)
        }
        composable(bottomNav){
            BottomNavigation()
        }
    }
}

const val splash = "start_screen"
const val login = "login_screen"
const val signup = "signup_screen"
const val bottomNav = "bottomNav_screen"
const val add_menu = "add_menu_screen"
const val all_menu_show = "all_menu_show_screen"
const val out_for_delivery = "out_for_delivery_screen"
const val profile = "profile_screen"
const val create_user_admin = "create_user_admin_screen"
const val pending_order = "pending_order_screen"