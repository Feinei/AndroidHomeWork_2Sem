package com.example.androidhomework2sem

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.androidhomework2sem.City.City
import com.example.androidhomework2sem.City.CityAdapter
import com.example.androidhomework2sem.response.WeatherResponse
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import java.util.*


class MainActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    private lateinit var api: WeatherAPI
    private lateinit var lm: LocationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val context = this
        var location: Location? = null

        api = ApiFactory.weatherApi
        lm = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
            checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
        ) {
            location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION), 1
            )
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 2
            )
        }

        launch {
            val cities = withContext(Dispatchers.IO) {
                if (location == null)
                    api.weatherInNearbyCities(49.12, 55.79, 20).body()?.list
                else
                    api.weatherInNearbyCities(location.longitude, location.latitude, 20).body()?.list
            }

            val adapter = CityAdapter {
                startActivity(
                    Intent(context, DetailActivity::class.java).putExtra(
                        "city_name",
                        it.name
                    )
                )
            }

            val citiesList: MutableList<City> = mutableListOf()

            cities?.let {
                for (city in it)
                    citiesList.add(City(city.name, WeatherConverter.fromFtoC(city.main.temp)))
            }
            adapter.submitList(citiesList)
            rv_cities.adapter = adapter
        }

        setSearchView()
    }

    private fun setSearchView() {

        val context = this
        sv_city.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(s: String): Boolean {
                launch {
                    try {
                        val response = withContext(Dispatchers.IO) {
                            api.weatherByName(s)
                        }
                        startActivity(
                            Intent(
                                context,
                                DetailActivity::class.java
                            ).putExtra("city_name", s)
                        )
                        sv_city.queryHint = s
                    } catch (e: Exception) {
                        Snackbar.make(
                            findViewById(android.R.id.content),
                            "City \"$s\" is not found!",
                            Snackbar.LENGTH_SHORT
                        )
                            .show()
                    }
                }
                return false
            }

            override fun onQueryTextChange(s: String): Boolean {
                return false
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineContext.cancelChildren()
    }
}
