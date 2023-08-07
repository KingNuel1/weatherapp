package com.example.weatherapp.models

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("/data/2.5/weather")

    suspend fun getCurrentConditions(
        @Query(value = "zip") zip: String = "55442,us",
        @Query(value = "units") units: String = "imperial",
        @Query(value = "appid") appid: String = "5bc8528ce75ec127f8fc1a8645f239c2"
    ) : CurrentConditions

    @GET("/data/2.5/forecast/daily")
    suspend fun getForecast(
        @Query(value = "zip") zip: String = "55442,us",
        @Query(value = "units") units: String = "imperial",
        @Query(value = "cnt") count: Int = 16,
        @Query(value = "appid") appID: String = "5bc8528ce75ec127f8fc1a8645f239c2"
    ) : Forecast

}