package com.example.weatherapp.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.models.ApiService
import com.example.weatherapp.models.Forecast
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForecastViewModel @Inject constructor(private val apiService: ApiService): ViewModel() {
    private val _forecast: MutableLiveData<Forecast> = MutableLiveData()
    val forecast: LiveData<Forecast>
        get() = _forecast

    fun viewAppeared() = viewModelScope.launch {
        _forecast.value = apiService.getForecast()
    }
}