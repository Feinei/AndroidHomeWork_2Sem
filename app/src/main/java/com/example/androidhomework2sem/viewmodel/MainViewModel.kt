package com.example.androidhomework2sem.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidhomework2sem.model.api.Food
import com.example.androidhomework2sem.model.repository.FoodRepository
import com.example.androidhomework2sem.utils.Constants
import com.example.androidhomework2sem.utils.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(private val foodRepository: FoodRepository) : ViewModel() {

    private val food = MutableLiveData<Resource<List<Food>>>()
    private val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun getFood(): LiveData<Resource<List<Food>>> = food

    fun fetchFood(name: String) {
        food.postValue(Resource.loading(null))
        compositeDisposable.add(
            foodRepository.searchFood(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ foodList ->
                    food.postValue(Resource.success(foodList.hints))
                }, {
                    food.postValue(Resource.error(Constants.MSG_NETWORK_ERROR, null))
                })
        )
    }
}
