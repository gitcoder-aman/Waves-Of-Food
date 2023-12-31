package com.tech.wavesfood.screens

import androidx.compose.ui.geometry.Size
import android.widget.Toast
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowCircleDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.tech.wavesfood.firebase.firebaseAuth.AuthUserModel
import com.tech.wavesfood.firebase.firebaseAuth.ui.AuthViewModel
import com.tech.wavesfood.firebase.utils.ResultState
import com.tech.foodorderAdminapp.util.showMsg
import com.tech.wavesfood.R
import com.tech.wavesfood.common.CommonDialog
import com.tech.wavesfood.common.TextDesignByAman
import com.tech.wavesfood.common.lato_regular
import com.tech.wavesfood.common.yeon_sung_regular
import com.tech.wavesfood.navigation.login
import com.tech.wavesfood.ui.theme.GreenColor
import com.tech.wavesfood.ui.theme.WavesFoodTheme
import com.tech.wavesfood.ui.theme.darkWhiteColor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun SignupScreen(navHostController: NavHostController) {

    val scrollState = rememberScrollState()
    var userName by remember {
        mutableStateOf("")
    }
    var email by rememberSaveable {
        mutableStateOf("")
    }
    var phone by rememberSaveable {
        mutableStateOf("")
    }
    var password by rememberSaveable {
        mutableStateOf("")
    }
    val authViewModel: AuthViewModel = hiltViewModel()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var isDialog by remember {
        mutableStateOf(false)
    }

    if (isDialog) {
        CommonDialog()
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(darkWhiteColor),
        contentAlignment = Alignment.BottomCenter
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(top = 30.dp, start = 20.dp, end = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Header(stringResource(R.string.signup_here_for_you))

            Spacer(modifier = Modifier.height(10.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                Text(
                    text = stringResource(R.string.choose_your_location), style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W600,
                        fontFamily = yeon_sung_regular,
                        color = GreenColor
                    )
                )
            }
            val location = locationLayout()
            Spacer(modifier = Modifier.height(10.dp))

            TextFieldLayout(
                text = userName,
                textFieldTitle = stringResource(R.string.name),
                icon = R.drawable.usernew,
                onValueChange = {
                    userName = it
                })
            Spacer(modifier = Modifier.height(10.dp))

            TextFieldLayout(
                text = email,
                textFieldTitle = stringResource(id = R.string.email),
                icon = R.drawable.mail,
                onValueChange = {
                    email = it
                })
            Spacer(modifier = Modifier.height(10.dp))

            TextFieldLayout(
                text = phone,
                textFieldTitle = stringResource(R.string.phone),
                icon = R.drawable.phone,
                onValueChange = {
                    phone = it
                })
            Spacer(modifier = Modifier.height(10.dp))

            TextFieldLayout(
                text = password,
                textFieldTitle = stringResource(R.string.password),
                icon = R.drawable.lock,
                onValueChange = {
                    password = it
                })

            Spacer(modifier = Modifier.height(10.dp))

            LoginBtnAndText(
                stringResource(R.string.create_account),
                stringResource(R.string.already_have_an_account), noAccount = {
                    navHostController.navigate(login)
                }, login = {

                    if (email.isNotEmpty() && password.isNotEmpty()) {
                        scope.launch(Dispatchers.Main) {
                            authViewModel.createUser(
                                AuthUserModel(
                                    email = email,
                                    password = password,
                                    userName = userName,
                                    userAddress = location,
                                    userPhone = phone
                                )
                            ).collect {
                                isDialog = when (it) {
                                    is ResultState.Success -> {
                                        context.showMsg(it.data)
                                        navHostController.navigate(login)
                                        false
                                    }

                                    is ResultState.Failure -> {
                                        context.showMsg(it.msg.toString())
                                        false
                                    }

                                    is ResultState.Loading -> {
                                        true
                                    }
                                }
                            }
                        }
                    }else{
                        Toast.makeText(context, "Email & Password must be entered!", Toast.LENGTH_SHORT).show()
                    }
                })

            TextDesignByAman()

        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun locationLayout() : String {

    val listOptions =
        listOf(
            "Delhi",
            "Mumbai",
            "Chennai",
            "Kolkata",
            "Hyderabad",
            "Bengaluru",
            "Pune",
            "Bihar",
            "Lucknow",
            "Punjab"
        )
    var expandedState by remember {
        mutableStateOf(false)
    }
    var selectionOption by remember {
        mutableStateOf(listOptions[0])
    }
    var mTextFieldSize by remember {
        mutableStateOf(Size.Zero)
    }

    TextField(
        value = selectionOption,
        onValueChange = { selectionOption = it },
        modifier = Modifier
            .padding(0.dp)
            .shadow(elevation = 1.dp, shape = RoundedCornerShape(8.dp))
            .fillMaxWidth()
            .onGloballyPositioned { coordinates ->
                // This value is used to assign to
                // the DropDown the same width
                mTextFieldSize = coordinates.size.toSize()
            },
        shape = RoundedCornerShape(8.dp),
        textStyle = TextStyle(fontFamily = lato_regular),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            containerColor = Color.White,
            cursorColor = GreenColor,
            focusedTrailingIconColor = Color.Black,
            selectionColors = TextSelectionColors(
                handleColor = GreenColor,
                backgroundColor = GreenColor
            )
        ),
        readOnly = true,
        placeholder = {
            Text(
                stringResource(R.string.location_choose), style = TextStyle(
                    color = Color.Gray,
                    fontSize = 10.sp,
                    fontFamily = lato_regular,
                    textAlign = TextAlign.Center
                ), textAlign = TextAlign.Center
            )
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.location),
                contentDescription = "",
                tint = Color.Unspecified
            )
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.Filled.ArrowCircleDown,
                contentDescription = "",
                tint = GreenColor,
                modifier = Modifier.clickable { expandedState = !expandedState }
            )
        }
    )
    DropdownMenu(
        expanded = expandedState, onDismissRequest = { expandedState = false },
        modifier = Modifier
            .width(with(LocalDensity.current) { mTextFieldSize.width.toDp() })
            .background(darkWhiteColor)
    ) {

        listOptions.forEach { eachoption ->
            DropdownMenuItem(text = {
                Text(
                    text = eachoption, color = Color.Black, style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = lato_regular,
                        fontWeight = FontWeight.W400
                    )
                )
            }, onClick = {
                selectionOption = eachoption
                expandedState = false
            })
        }
    }
    return selectionOption
}

@Composable
fun SignupPreview() {
    WavesFoodTheme {
        val navHostController = rememberNavController()
//        SignupScreen(navHostController = navHostController)
    }
}