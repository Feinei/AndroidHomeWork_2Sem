package com.example.androidhomework2sem.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidhomework2sem.model.api.ApiFactory
import com.example.androidhomework2sem.model.repository.FoodRepository
import com.example.androidhomework2sem.utils.Constants

class ViewModelFactory(private val api: ApiFactory) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(FoodRepository(api)) as T
        }
        throw IllegalArgumentException(Constants.ERROR_UNKNOWN)
    }
}
