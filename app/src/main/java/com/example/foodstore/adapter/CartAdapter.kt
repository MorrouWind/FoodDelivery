package com.example.foodstore.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodstore.databinding.ViewHolderCartBinding
import com.example.foodstore.db.service.ManagementCart
import com.example.foodstore.model.ChangeNumberItemsListener
import com.example.foodstore.model.PopularFoodModel
import java.math.RoundingMode
import java.text.DecimalFormat

class CartAdapter(
    val popularFoods: MutableList<PopularFoodModel>,
    val context: Context,
    val cniListener: ChangeNumberItemsListener
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    private val mCart = ManagementCart(context)

    inner class CartViewHolder(val binding: ViewHolderCartBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.CartViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewHolderCartBinding.inflate(layoutInflater, parent, false)
        return CartViewHolder(binding)
    }


    override fun getItemCount(): Int {
        return popularFoods.size
    }

    override fun onBindViewHolder(holder: CartAdapter.CartViewHolder, position: Int) {
        with(holder.binding) {
            titleCart.text = popularFoods[position].title
            feeItemCart.text = roundOfDecimal(popularFoods[position].fee).toString()
            feeAllItemsCart.text =
                roundOfDecimal((popularFoods[position].fee) * (popularFoods[position].quantity)).toString()
            quantityCart.text = (popularFoods[position].quantity).toString()

            val drawResourceId = picCart.context.resources.getIdentifier((popularFoods[position].pic), "drawable", picCart.context.packageName)

            Glide.with(picCart.context)
                .load(drawResourceId)
                .into(picCart)

            plusCart.setOnClickListener {
            mCart.plusNumberFood(popularFoods, position, object : ChangeNumberItemsListener {
                override fun changed() {
                    notifyDataSetChanged()
                    cniListener.changed()
                }
            })
            }
            minusCart.setOnClickListener {
                mCart.minusNumberFood(popularFoods, position, object : ChangeNumberItemsListener {
                    override fun changed() {
                        notifyDataSetChanged()
                        cniListener.changed()
                    }
                })
            }
        }
    }

    private fun roundOfDecimal(num: Double): Double {
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.FLOOR
        return df.format(num).toDouble()
    }

}