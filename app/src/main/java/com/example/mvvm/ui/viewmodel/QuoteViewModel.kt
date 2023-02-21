package com.example.mvvm.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm.data.model.QuoteModel
import com.example.mvvm.data.model.QuoteProvider
import com.example.mvvm.domain.GetQuotesUseCase
import com.example.mvvm.domain.GetRandomQuoteUseCase
import kotlinx.coroutines.launch

class QuoteViewModel:ViewModel() {

    val quoteModel = MutableLiveData<QuoteModel>()
    val isloading = MutableLiveData<Boolean>()

    var getQuotesUseCase = GetQuotesUseCase()
    var getRandomQuoteUseCase = GetRandomQuoteUseCase()

    fun onCreate() {
        viewModelScope.launch {
            isloading.postValue(true)
            val result = getQuotesUseCase()

            if (!result.isNullOrEmpty()){
                quoteModel.postValue(result[0])
                isloading.postValue(false)
            }
        }
    }

    fun randomQuote(){
        isloading.postValue(true)
        val quote:QuoteModel? = getRandomQuoteUseCase()
        if (quote != null){
            quoteModel.postValue(quote)
        }
        isloading.postValue(false)
        //val currentQuote = QuoteProvider.random()
        //quoteModel.postValue(currentQuote)
    }
}