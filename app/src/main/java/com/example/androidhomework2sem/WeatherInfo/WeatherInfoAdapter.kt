package com.example.androidhomework2sem.WeatherInfo

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

class WeatherInfoAdapter(
    private val itemClickLambda: (WeatherInfo) -> Unit
) : ListAdapter<WeatherInfo, WeatherInfoItemHolder>(object : DiffUtil.ItemCallback<WeatherInfo>() {

    override fun areItemsTheSame(oldItem: WeatherInfo, newItem: WeatherInfo): Boolean =
        oldItem.name == newItem.name

    override fun areContentsTheSame(oldItem: WeatherInfo, newItem: WeatherInfo): Boolean =
        oldItem == newItem
}) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherInfoItemHolder =
        WeatherInfoItemHolder.create(parent, itemClickLambda)

    override fun onBindViewHolder(holder: WeatherInfoItemHolder, position: Int) =
        holder.bind(getItem(position))
}
