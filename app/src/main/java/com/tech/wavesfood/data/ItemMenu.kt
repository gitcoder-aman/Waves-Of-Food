package com.tech.wavesfood.data

import com.tech.wavesfood.R

data class ItemMenu(
    val itemId : Int,
    val itemImage : Int,
    val itemName : String,
    val itemPrice : String,
    val shortDesc : String,
    val ingredients : String
)
val itemMenuList = listOf(
    ItemMenu(1, R.drawable.menu1,"Herbal Pancake fadshfklhasdl fjah","$10","short desc","ingredients"),
    ItemMenu(2, R.drawable.menu2,"Fruite Salad","$20","short desc","ingredients"),
    ItemMenu(3, R.drawable.menu3,"Green Noddle","$40","short desc","ingredients"),
    ItemMenu(4, R.drawable.menu4,"Chocolate cake","$50","short desc","ingredients"),
    ItemMenu(5, R.drawable.menu4,"Chocolate cake","$50","short desc","ingredients"),
    ItemMenu(6, R.drawable.menu4,"Chocolate cake","$50","short desc","ingredients"),
    ItemMenu(7, R.drawable.menu4,"Chocolate cake","$50","short desc","ingredients"),
    ItemMenu(8, R.drawable.menu4,"Chocolate cake","$50","short desc","ingredients"),
    ItemMenu(9, R.drawable.menu4,"Chocolate cake","$50","short desc","ingredients"),
    ItemMenu(10, R.drawable.menu4,"Chocolate cake","$50","short desc","ingredients"),
)
