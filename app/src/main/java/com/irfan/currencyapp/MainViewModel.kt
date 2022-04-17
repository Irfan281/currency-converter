package com.irfan.currencyapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.irfan.currencyapp.api.ApiConfig
import com.irfan.currencyapp.api.CurrencyData
import com.irfan.currencyapp.api.Response
import retrofit2.Call
import retrofit2.Callback

class MainViewModel: ViewModel() {
    private val _data = MutableLiveData<CurrencyData>()
    val data: LiveData<CurrencyData> = _data

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading


    fun getRates(api: String, base: String, currencies: String) {
        _isLoading.value = true

        val client = ApiConfig.getApiService().getRates(api, base, currencies)
        client.enqueue(object : Callback<Response> {
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _data.value = response.body()?.data

                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    companion object {
        const val TAG = "MainViewModel"
    }
}