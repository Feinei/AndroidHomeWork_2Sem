package com.example.androidhomework2sem.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidhomework2sem.R
import com.example.androidhomework2sem.model.api.ApiFactory
import com.example.androidhomework2sem.utils.Status
import com.example.androidhomework2sem.view.adapter.FoodAdapter
import com.example.androidhomework2sem.viewmodel.MainViewModel
import com.example.androidhomework2sem.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var adapter: FoodAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupUI()
        setupViewModel()
    }

    private fun callAPI(foodName: String) {
        mainViewModel.getFood().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    loading_indicator.visibility = View.GONE
                    adapter.submitList(it.data)
                    rv_food.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    loading_indicator.visibility = View.VISIBLE
                    rv_food.visibility = View.GONE
                }
                Status.ERROR -> {
                    loading_indicator.visibility = View.VISIBLE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
        mainViewModel.fetchFood(foodName)
    }

    private fun setupUI() {
        supportActionBar?.hide()

        rv_food.layoutManager = LinearLayoutManager(this)
        adapter = FoodAdapter(this) { }
        adapter.submitList(arrayListOf())
        rv_food.adapter = adapter

        sv_food.setOnQueryChangeListener { oldQuery, newQuery ->
            if (newQuery != "" && newQuery != null)
                callAPI(newQuery)
        }
    }

    private fun setupViewModel() {
        mainViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiFactory)
        ).get(MainViewModel::class.java)
    }
}
