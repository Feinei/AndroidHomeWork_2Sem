package com.example.androidhomework2sem.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidhomework2sem.R
import com.example.androidhomework2sem.model.api.Food
import com.example.androidhomework2sem.utils.UtilMethods
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_food.*

class FoodItemHolder(
    override val containerView: View,
    private val clickLambda: (Food) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    companion object {
        fun create(parent: ViewGroup, clickLambda: (Food) -> Unit) =
            FoodItemHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_food,
                    parent,
                    false
                ),
                clickLambda
            )
    }

    fun bind(food: Food, context: Context) {

        var foodName = food.foodDetail.label
        foodName[0].toUpperCase()
        if (food.foodDetail.label.length >= 17)
            foodName = foodName.take(17) + "..."
        tv_name.text = foodName

        tv_cals.text = context.getString(
            R.string.cals_param,
            UtilMethods.roundDoubleToOneDigit(food.foodDetail.nutrients.cals).toString()
        )
        tv_carbs.text = context.getString(
            R.string.carbs_param,
            UtilMethods.roundDoubleToOneDigit(food.foodDetail.nutrients.carbs).toString()
        )
        tv_fat.text = context.getString(
            R.string.fat_param,
            UtilMethods.roundDoubleToOneDigit(food.foodDetail.nutrients.fat).toString()
        )
        tv_protein.text = context.getString(
            R.string.protein_param,
            UtilMethods.roundDoubleToOneDigit(food.foodDetail.nutrients.protein).toString()
        )

        if (food.foodDetail.imageSource != null)
            Glide.with(iv_icon.context)
                .load(food.foodDetail.imageSource)
                .into(iv_icon)
        else
            iv_icon.setImageResource(R.drawable.ic_food_black_24dp)

        itemView.setOnClickListener {
            clickLambda(food)
        }
    }
}
