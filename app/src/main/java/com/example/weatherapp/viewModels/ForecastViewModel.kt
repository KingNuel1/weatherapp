package com.example.weatherapp.viewModels

import android.util.Log
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
    val userZip: MutableLiveData<String> = MutableLiveData("55442")
    private val _forecast: MutableLiveData<Forecast> = MutableLiveData()
    val forecast: LiveData<Forecast>
        get() = _forecast

    fun viewAppeared() = viewModelScope.launch {
        _forecast.value = apiService.getForecast(zip = userZip.value.toString() + ",us")
    }

    fun validateZip(zip : String?): Boolean {
        if (
            (zip.isNullOrEmpty() || zip.length != 5) || (zip.any() { !it.isDigit() })) {
            return false
        } else {
            Log.d("forecastVM.validateZip()", "valid Zip")
            userZip.value = zip
            viewAppeared()
            return true
        }
    }
}