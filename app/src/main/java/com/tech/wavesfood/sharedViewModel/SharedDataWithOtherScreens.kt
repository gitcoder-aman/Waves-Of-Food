package com.tech.wavesfood.sharedViewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.tech.wavesfood.R

class SharedDataWithOtherScreens : ViewModel(){
    private val _itemImage : MutableState<Int> = mutableIntStateOf(R.drawable.no_image)
    private val _shortDesc : MutableState<String> = mutableStateOf("")
    private val _ingredients : MutableState<String> = mutableStateOf("")

    private val _cartList : ArrayList<Int> = ArrayList()

    val itemImage: State<Int> = _itemImage
    val shortDesc: State<String> = _shortDesc
    val ingredients: State<String> = _ingredients
    val cartList : ArrayList<Int> = _cartList

    fun setData(itemImage:Int,shortDesc:String,ingredients:String){
        _itemImage.value = itemImage
        _shortDesc.value = shortDesc
        _ingredients.value = ingredients
    }
    fun addToCartList(itemId : Int){
        _cartList.add(itemId)
    }

}