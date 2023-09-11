package com.example.foodstore.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodstore.databinding.ViewHolderPopularBinding
import com.example.foodstore.model.PopularFoodModel
import com.example.foodstore.screen.ShowDetailActivity

class PopularAdapter(
    private val popularFoods: MutableList<PopularFoodModel>
) : RecyclerView.Adapter<PopularAdapter.PopularViewHolder>() {

    inner class PopularViewHolder(val binding: ViewHolderPopularBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewHolderPopularBinding.inflate(inflater, parent, false)
        return PopularViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return popularFoods.size
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        with(holder.binding) {
            title.text = popularFoods[position].title
            fee.text = popularFoods[position].fee.toString()

            val drawResourcesId = pic.context.resources.getIdentifier((popularFoods[position].pic), "drawable", pic.context.packageName)
            Glide.with(pic.context)
                .load(drawResourcesId)
                .into(pic)


            fun toDetail() {
                val intent = Intent(popularFoodLayout.context, ShowDetailActivity::class.java)
                intent.putExtra("object", popularFoods[position])
                popularFoodLayout.context.startActivity(intent)
            }

            addBtn.setOnClickListener {
                toDetail()
            }

            popularFoodLayout.setOnClickListener {
                toDetail()
            }
        }
    }

}