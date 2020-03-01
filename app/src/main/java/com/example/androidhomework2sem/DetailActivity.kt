package com.example.androidhomework2sem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.Toolbar
import com.example.androidhomework2sem.WeatherInfo.WeatherInfo
import com.example.androidhomework2sem.WeatherInfo.WeatherInfoAdapter
import com.example.androidhomework2sem.response.WeatherList
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.coroutines.*
import retrofit2.Response

class DetailActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    private lateinit var api: WeatherAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        api = ApiFactory.weatherApi

        launch {
            val response = withContext(Dispatchers.IO) {
                api.weatherByName(intent.getStringExtra("city_name"))
            }

            tv_main.text = response.weathers[0].main
            tv_description.text = response.weathers[0].description

            img_icon.setImageResource(when(response.weathers[0].main) {
                "Sunny" -> R.drawable.ic_wi_sunny
                "Clouds" -> R.drawable.ic_wi_cloudy
                "Rain" -> R.drawable.ic_wi_rain
                else -> R.drawable.ic_wi_na
            })

            val adapter = WeatherInfoAdapter{}
            adapter.submitList(listOf(
                WeatherInfo("Temperature", R.drawable.ic_wi_thermometer, WeatherConverter.fromFtoC(response.main.temp).toString()),
                WeatherInfo("Feels like", R.drawable.ic_wi_thermometer, WeatherConverter.fromFtoC(response.main.feelsLike).toString()),
                WeatherInfo("Max.temperature", R.drawable.ic_wi_thermometer, WeatherConverter.fromFtoC(response.main.tempMax).toString()),
                WeatherInfo("Min.temperature", R.drawable.ic_wi_thermometer, WeatherConverter.fromFtoC(response.main.tempMin).toString()),
                WeatherInfo("Humidity", R.drawable.ic_wi_humidity, response.main.humidity.toString()),
                WeatherInfo("Wind speed", R.drawable.ic_wi_strong_wind, response.wind.speed.toString()),
                WeatherInfo("Wind direction", R.drawable.ic_wi_strong_wind, WeatherConverter.getWindDirection(response.wind.deg))))
            rv_weather_info.adapter = adapter
        }
    }
}
