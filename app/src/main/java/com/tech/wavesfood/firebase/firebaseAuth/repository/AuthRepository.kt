package com.tech.wavesfood.firebase.firebaseAuth.repository

import com.tech.wavesfood.firebase.firebaseAuth.AuthUserModel
import com.tech.wavesfood.firebase.utils.ResultState
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun createUser(authUserModel: AuthUserModel):Flow<ResultState<String>>

    fun loginUser(authUserModel: AuthUserModel):Flow<ResultState<String>>

    fun logout():Flow<ResultState<String>>

}