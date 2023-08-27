package com.tech.wavesfood.sharedViewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.tech.wavesfood.R

class SharedDataWithOtherScreens : ViewModel(){
    private val _itemImage : MutableState<Int> = mutableIntStateOf(R.drawable.no_image)
    private var _itemId : MutableState<Int> = mutableIntStateOf(0)
    private var _itemPrice : MutableState<Int> = mutableIntStateOf(0)
    private val _shortDesc : MutableState<String> = mutableStateOf("")
    private val _ingredients : MutableState<String> = mutableStateOf("")
    private val _cartList = mutableSetOf<Int>()

    val itemImage: State<Int> = _itemImage
    val itemId: State<Int> = _itemId
    val shortDesc: State<String> = _shortDesc
    val itemPrice: State<Int> = _itemPrice
    val ingredients: State<String> = _ingredients
    val cartList : MutableSet<Int> = _cartList

    fun setData(itemId: Int,itemImage:Int,shortDesc:String,ingredients:String,itemPrice:Int){
        _itemId.value = itemId
        _itemImage.value = itemImage
        _shortDesc.value = shortDesc
        _ingredients.value = ingredients
        _itemPrice.value = itemPrice
    }
    fun addToCartList(itemId : Int){
        _cartList.add(itemId)
    }
    fun removeItem(index : Int) {
        _cartList.remove(index)
    }

}