package com.tech.wavesfood.firebase.firebaseAuth

data class AuthUserModel(
    var email : String = "",
    var password : String = "",
    var userName : String = "",
    var userAddress : String = "",
    var userPhone : String = "",
    var uid : String = "",
)
