package com.irfan.currencyapp.api

import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Call

interface ApiService {
    @GET("latest")
    fun getRates(
        @Query("apikey") api: String,
        @Query("base_currency") base: String,
        @Query("currencies") currencies: String,
    ): Call<Response>
}