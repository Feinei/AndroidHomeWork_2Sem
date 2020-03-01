package com.example.androidhomework2sem.City

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidhomework2sem.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_city.*

class CityItemHolder(
    override val containerView : View,
    private val clickLambda: (City) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    companion object {
        fun create(parent: ViewGroup, clickLambda: (City) -> Unit) =
            CityItemHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_city, parent, false),
                clickLambda
            )
    }

    fun bind(city : City) {
        tv_name.text = city.name
        tv_temp.text = city.temp.toString()
        tv_temp.setTextColor(when(city.temp)
        {
            in -100..-15 -> R.color.colorExtraCold
            in -15..-5 -> R.color.colorCold
            in 5..15 -> R.color.colorWarm
            in 15..100 -> R.color.colorExtraWarm
            else -> R.color.colorZero
        })

        itemView.setOnClickListener {
            clickLambda(city)
        }
    }
}