package com.example.androidhomework2sem.WeatherInfo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidhomework2sem.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_weather_info.*

class WeatherInfoItemHolder(
    override val containerView: View,
    private val clickLambda: (WeatherInfo) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    companion object {
        fun create(parent: ViewGroup, clickLambda: (WeatherInfo) -> Unit) =
            WeatherInfoItemHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_weather_info,
                    parent,
                    false
                ),
                clickLambda
            )
    }

    fun bind(info: WeatherInfo) {
        tv_info_name.text = info.name
        tv_info.text = info.info
        iv_info_icon.setImageResource(info.iconId)

        itemView.setOnClickListener {
            clickLambda(info)
        }
    }
}
