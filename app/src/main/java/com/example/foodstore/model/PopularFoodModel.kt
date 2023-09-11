package com.example.foodstore.model

import java.io.Serializable

data class PopularFoodModel(
    val id: Int,
    val title: String,
    val pic: String,
    val description: String,
    val fee: Double,
    var quantity: Int
) : Serializable