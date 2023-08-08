package com.example.weatherapp.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.models.ApiService
import com.example.weatherapp.models.CurrentConditions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrentConditionsViewModel @Inject constructor(private val apiService: ApiService) : ViewModel() {

    val userZip: MutableLiveData<String> = MutableLiveData("55442")
    val showInvalidZipWarning: MutableLiveData<Boolean> = MutableLiveData(false)
    private val _currentConditions: MutableLiveData<CurrentConditions> = MutableLiveData()
    val currentConditions: LiveData<CurrentConditions>
        get() = _currentConditions
    fun viewAppeared(zip: String? = userZip.value) = viewModelScope.launch {
        _currentConditions.value = apiService.getCurrentConditions(zip.toString() + ",us")
    }
    fun validateZipAndUpdate(): Boolean {
        val currentUserInput = userZip.value
        if (
            (currentUserInput.isNullOrEmpty() || currentUserInput.length != 5) || (currentUserInput.any() { !it.isDigit() })) {
            userZip.value = "55119"
            return false
        } else {
            Log.d("validateZipAndUpdate()", "valid Zip")
            viewAppeared()
            return true
        }
    }

}