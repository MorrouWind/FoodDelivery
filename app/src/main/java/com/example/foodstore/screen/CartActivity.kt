package com.example.foodstore.screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodstore.adapter.CartAdapter
import com.example.foodstore.databinding.ActivityCartBinding
import com.example.foodstore.db.service.ManagementCart
import com.example.foodstore.model.ChangeNumberItemsListener
import java.math.RoundingMode
import java.text.DecimalFormat

class CartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCartBinding

    private lateinit var adapter: CartAdapter
    private lateinit var mCart: ManagementCart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mCart = ManagementCart(this)

        initList()
        calculateCart()
        bottomNavigation()
    }

    private fun bottomNavigation() {
        with(binding) {
            cartBtn.setOnClickListener {
                startActivity(Intent(this@CartActivity, CartActivity::class.java))
            }
            homeBtn.setOnClickListener {
                startActivity(Intent(this@CartActivity, MainActivity::class.java))
            }
        }
    }


    private fun initList() {
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = CartAdapter(mCart.getListCart(), this, object : ChangeNumberItemsListener {

            override fun changed() {
                calculateCart()
            }
        })
        binding.recyclerViewCart.layoutManager = layoutManager
        binding.recyclerViewCart.adapter = adapter

        if (mCart.getListCart().isEmpty()) {
//            binding.emptyCart.visibility = View.VISIBLE
            binding.scrollView.visibility = View.GONE
        } else {
            binding.emptyCart.visibility = View.GONE
            binding.scrollView.visibility = View.VISIBLE
        }

    }

    fun calculateCart() {

        val percentTax = 0.02
        val delivery = 10

        val tax = mCart.getTotalFee() * percentTax

        val total = mCart.getTotalFee() + tax + delivery
        val itemTotal = mCart.getTotalFee()

        with(binding) {
            totalFeeTxt.text = "$ ${roundOfDecimal(itemTotal)}"
            taxTxt.text = "$ ${roundOfDecimal(tax)}"
            deliveryTxt.text = "$ $delivery"
            totalTxt.text = "$ ${roundOfDecimal(total)}"

        }

    }

    private fun roundOfDecimal(num: Double): Double {
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.FLOOR
        return df.format(num).toDouble()
    }
}