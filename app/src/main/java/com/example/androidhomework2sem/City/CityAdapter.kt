package com.example.androidhomework2sem.City

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

class CityAdapter(
    private val itemClickLambda: (City) -> Unit
) : ListAdapter<City, CityItemHolder>(object : DiffUtil.ItemCallback<City>() {

    override fun areItemsTheSame(oldItem: City, newItem: City): Boolean =
        oldItem.name == newItem.name

    override fun areContentsTheSame(oldItem: City, newItem: City): Boolean = oldItem == newItem
}) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityItemHolder =
        CityItemHolder.create(parent, itemClickLambda)

    override fun onBindViewHolder(holder: CityItemHolder, position: Int) =
        holder.bind(getItem(position))
}
