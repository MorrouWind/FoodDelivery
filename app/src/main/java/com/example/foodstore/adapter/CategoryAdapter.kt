package com.example.foodstore.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.foodstore.R
import com.example.foodstore.databinding.ViewHolderCategoryBinding
import com.example.foodstore.model.CategoryModel

class CategoryAdapter(
    val categoryModels: ArrayList<CategoryModel>
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

inner class CategoryViewHolder(val binding: ViewHolderCategoryBinding):
        RecyclerView.ViewHolder(binding.root) {

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewHolderCategoryBinding.inflate(inflater, parent, false)
        return CategoryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return categoryModels.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        with(holder.binding) {
            categoryName.text = categoryModels[position].title
            when(position) {
                0 -> {
                    categoryPic.setImageResource(R.drawable.cat_1)
                    mainLayout.background = ContextCompat.getDrawable(mainLayout.context, R.drawable.cat_background_1 )
                }
                1 -> {
                    categoryPic.setImageResource(R.drawable.cat_2)
                    mainLayout.background = ContextCompat.getDrawable(mainLayout.context, R.drawable.cat_background_2 )
                }
                2 -> {
                    categoryPic.setImageResource(R.drawable.cat_3)
                    mainLayout.background = ContextCompat.getDrawable(mainLayout.context, R.drawable.cat_background_3 )
                }
                3 -> {
                    categoryPic.setImageResource(R.drawable.cat_4)
                    mainLayout.background = ContextCompat.getDrawable(mainLayout.context, R.drawable.cat_background_4 )
                }
                4 -> {
                    categoryPic.setImageResource(R.drawable.cat_5)
                    mainLayout.background = ContextCompat.getDrawable(mainLayout.context, R.drawable.cat_background_5 )
                }
            }

        }


    }

}