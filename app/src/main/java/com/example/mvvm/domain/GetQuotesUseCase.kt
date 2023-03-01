package com.example.mvvm.domain

import com.example.mvvm.data.QuoteRepository
import com.example.mvvm.data.model.QuoteModel
import com.example.mvvm.domain.model.Quote
import com.example.mvvm.data.database.QuoteDatabase
import com.example.mvvm.data.database.entities.toDatabase
import javax.inject.Inject

class GetQuotesUseCase @Inject constructor(private val repository: QuoteRepository){

    //private val repository = QuoteRepository()

    suspend operator  fun invoke():List<Quote>{
        val quotes = repository.getAllQuotesFromApi()

        return if(quotes.isNotEmpty()){
            repository.clearQuotes()
            repository.insertQuotes(quotes.map { it.toDatabase() })
            quotes
        }else{
            repository.getAllQuotesFromDatabase()
        }
    }

}
