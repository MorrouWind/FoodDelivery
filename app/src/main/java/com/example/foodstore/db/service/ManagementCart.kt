package com.example.foodstore.db.service

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.foodstore.db.MiniDB
import com.example.foodstore.model.ChangeNumberItemsListener
import com.example.foodstore.model.PopularFoodModel


const val CART_LIST = "CartList"

class ManagementCart(
    val context: Context
) {
    private val db = MiniDB(context)

    fun insertFood(item: PopularFoodModel) {

        val popularFoods: MutableList<PopularFoodModel> = getListCart()
        var existsAlready = false
        var n = 0

        for (food in popularFoods) {
            if (food.id == item.id) {
                existsAlready = true
                n = food.id
                break
            }
        }
        if (existsAlready) {
            popularFoods[n].quantity = item.quantity
        } else {
            popularFoods.add(item)
        }


        db.putListObject(CART_LIST, popularFoods)
        Toast.makeText(context, "Added To Your Cart", Toast.LENGTH_SHORT).show()
    }

    fun getListCart() : MutableList<PopularFoodModel> {
        return db.getListObject(CART_LIST)
    }

    fun plusNumberFood(
        popularFoods: MutableList<PopularFoodModel>,
        position: Int,
        cniListener: ChangeNumberItemsListener
    ) {
        popularFoods[position].quantity += 1
        db.putListObject(CART_LIST, popularFoods)
        cniListener.changed()
    }

    fun minusNumberFood(
        popularFoods: MutableList<PopularFoodModel>,
        position: Int,
        cniListener: ChangeNumberItemsListener
    ) {
        if (popularFoods[position].quantity <= 1) {
            val indexToDelete = popularFoods.indexOfFirst { it.id == popularFoods[position].id }
            popularFoods.removeAt(indexToDelete)
            db.removeFood(CART_LIST)
            db.putListObject(CART_LIST, popularFoods)
            cniListener.changed()


        }else {
            popularFoods[position].quantity -= 1
            db.putListObject(CART_LIST, popularFoods)
            cniListener.changed()
        }
    }



    fun getTotalFee(): Double {
        val popularFoods: MutableList<PopularFoodModel> = getListCart()
        var fee = 0.0
        for (food in popularFoods) {
            fee += food.quantity * food.fee
        }
        return fee
    }

}