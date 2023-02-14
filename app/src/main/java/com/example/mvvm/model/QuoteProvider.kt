package com.example.mvvm.model

class QuoteProvider {
    companion object {

        fun random():QuoteModel{
            val position:Int = (0..2).random()
            return quote[position]
        }

        private val quote = listOf<QuoteModel>(
            QuoteModel(quote = "Ejemplo 1", autor = "Wilmer"),
            QuoteModel(quote = "Ejemplo 2", autor = "Damian"),
            QuoteModel(quote = "Ejemplo 3", autor = "Tamay")
        )
    }
}