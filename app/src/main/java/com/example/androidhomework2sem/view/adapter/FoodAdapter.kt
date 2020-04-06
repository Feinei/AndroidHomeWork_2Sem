package com.example.androidhomework2sem.view.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.androidhomework2sem.adapter.FoodItemHolder
import com.example.androidhomework2sem.model.api.Food

class FoodAdapter(
    private val context: Context,
    private val itemClickLambda: (Food) -> Unit
) : ListAdapter<Food, FoodItemHolder>(object : DiffUtil.ItemCallback<Food>() {

    override fun areItemsTheSame(oldItem: Food, newItem: Food): Boolean =
        oldItem.foodDetail.label == newItem.foodDetail.label

    override fun areContentsTheSame(oldItem: Food, newItem: Food): Boolean =
        oldItem == newItem
}) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodItemHolder =
        FoodItemHolder.create(
            parent,
            itemClickLambda
        )

    override fun onBindViewHolder(holder: FoodItemHolder, position: Int) =
        holder.bind(getItem(position), context)
}
