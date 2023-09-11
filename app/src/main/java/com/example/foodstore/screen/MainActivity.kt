package com.example.foodstore.screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodstore.R
import com.example.foodstore.adapter.CategoryAdapter
import com.example.foodstore.adapter.PopularAdapter
import com.example.foodstore.databinding.ActivityMainBinding
import com.example.foodstore.model.CategoryModel
import com.example.foodstore.model.PopularFoodModel

class MainActivity : AppCompatActivity() {

    private lateinit var adapterCtg: CategoryAdapter
    private lateinit var adapterPpl: PopularAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerViewCategory()
        recyclerViewPopular()
        bottomNavigation()

    }

    private fun bottomNavigation() {
        with(binding) {
            cartBtn.setOnClickListener {
                startActivity(Intent(this@MainActivity, CartActivity::class.java))
            }
            homeBtn.setOnClickListener {
                startActivity(Intent(this@MainActivity, MainActivity::class.java))
            }
        }
    }


    private fun recyclerViewCategory() {

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val category: ArrayList<CategoryModel> = ArrayList()
        category.add(CategoryModel("Pizza", R.drawable.cat_1))
        category.add(CategoryModel("Burger", R.drawable.cat_2))
        category.add(CategoryModel("Hot-Dog", R.drawable.cat_3))
        category.add(CategoryModel("Drink", R.drawable.cat_4))
        category.add(CategoryModel("Donut", R.drawable.cat_5))

        binding.recyclerViewCategory.layoutManager = layoutManager
        adapterCtg = CategoryAdapter(category)
        binding.recyclerViewCategory.adapter = adapterCtg
    }

    private fun recyclerViewPopular() {
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val popularList: MutableList<PopularFoodModel> = mutableListOf()
        popularList.add(PopularFoodModel(
            1,
            "Pepperoni pizza", "pop_1",
            "slices peperoni, mozzarella cheese, fresh oregano, ground black pepper, pizza sauce ",
            9.76,
            1
        ))
        popularList.add(PopularFoodModel(
            2,
            "Cheese Burger", "pop_2",
            "burger, beef, Gouda Cheese, Special Sauce, Lettuce, tomato",
            8.79,
            1
        ))
        popularList.add(PopularFoodModel(
            3,
            "Vegetable pizza", "pop_3",
            "Olive oil, vegetable oil, pitted kalamata, cherry tomatoes, fresh oregano, basil",
            8.55,
            1
        ))



        binding.recyclerViewPopular.layoutManager = layoutManager
        adapterPpl = PopularAdapter(popularList)
        binding.recyclerViewPopular.adapter = adapterPpl


    }

}