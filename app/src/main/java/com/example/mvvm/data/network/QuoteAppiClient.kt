package com.example.mvvm.data.network

import com.example.mvvm.data.model.QuoteModel
import okhttp3.Response
import retrofit2.http.GET

interface QuoteAppiClient {
    @GET("/.json")
    suspend fun getAllQuotes():retrofit2.Response<List<QuoteModel>>
}