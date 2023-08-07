package com.example.weatherapp.models

import com.squareup.moshi.Json

data class Forecast(
    @Json(name = "cnt") val count: Int,
    @Json(name = "list") val forecastList: List<DayForecast>
)

data class DayForecast(
    @Json(name = "dt") val date: Long,
    val sunrise: Long,
    val sunset: Long,
    @Json(name = "temp") private val tempData: ForecastTemp,
    @Json(name = "weather") private val weatherSummaryList: List<WeatherData>,
) {
    val daytimeTemp : Float
        get() = tempData.day
    val minTemp : Float
        get() = tempData.min
    val maxTemp : Float
        get() = tempData.max
    val weatherIconUrl : String
        get() = "https://openweathermap.org/img/wn/${weatherSummaryList.firstOrNull()?.icon}@2x.png"
}

data class ForecastTemp(
    val day: Float,
    val min: Float,
    val max: Float
)