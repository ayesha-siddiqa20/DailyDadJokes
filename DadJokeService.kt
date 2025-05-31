package com.example.dailydadjokes
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.Call


interface DadJokeService {

    @Headers(
        "Accept: application/json",
        "User-Agent: DailyDadJokes"
    )

    @GET("/")
    fun getRandomQuote(): Call<DadJoke>


}