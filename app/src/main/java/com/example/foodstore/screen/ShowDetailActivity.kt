package com.example.foodstore.screen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.foodstore.databinding.ActivityShowDetailBinding
import com.example.foodstore.db.service.ManagementCart
import com.example.foodstore.model.PopularFoodModel

class ShowDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShowDetailBinding

    private lateinit var popularFood: PopularFoodModel
    private var numOrder = 1
    private lateinit var mCart: ManagementCart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mCart = ManagementCart(this)

        getBundle()

    }

    private fun getBundle() {
        with(binding) {
            popularFood = intent.getSerializableExtra("object") as PopularFoodModel
            title.text = popularFood.title
            description.text = popularFood.description
            fee.text = "$ ${popularFood.fee}"
            quantity.text = numOrder.toString()

            val drawResourcesId = this@ShowDetailActivity.resources.getIdentifier(
                popularFood.pic,
                "drawable",
                this@ShowDetailActivity.packageName
            )
            Glide.with(this@ShowDetailActivity)
                .load(drawResourcesId)
                .into(pic)


            plus.setOnClickListener {
                numOrder += 1
                quantity.text = numOrder.toString()
            }

            minus.setOnClickListener {
                if (numOrder > 1) {
                    numOrder -= 1
                }
                quantity.text = numOrder.toString()
            }

            addToCartBtn.setOnClickListener {
                mCart.insertFood(popularFood)
            }

        }

    }

}