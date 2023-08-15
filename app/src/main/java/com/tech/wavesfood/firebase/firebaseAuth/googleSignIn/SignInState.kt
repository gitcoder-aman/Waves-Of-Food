package com.tech.wavesfood.firebase.firebaseAuth.googleSignIn

data class SignInState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null,
)
