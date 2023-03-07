package com.example.mvvm.domain

import com.example.mvvm.data.QuoteRepository
import com.example.mvvm.domain.model.Quote
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetRandomQuoteUseCaseTest{

    @RelaxedMockK
    private lateinit var  quoteRepository: QuoteRepository

    lateinit var quoteRandomQuoteUseCase: GetRandomQuoteUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        quoteRandomQuoteUseCase = GetRandomQuoteUseCase(quoteRepository)
    }

    @Test
    fun `when database is empty return null`() = runBlocking {
        //Given
        coEvery { quoteRepository.getAllQuotesFromDatabase() } returns emptyList()

        //When
        val response = quoteRandomQuoteUseCase

        //Then
        assert(response == null)
    }

    @Test
    fun `when the database is not empty then return quote`() = runBlocking {
        //Given
        val quoteList = listOf(Quote("hello", "Damian"))
        coEvery { quoteRepository.getAllQuotesFromDatabase() } returns quoteList

        //When
        val response = quoteRandomQuoteUseCase()

        //Then
        assert(response == quoteList.first())
    }
}