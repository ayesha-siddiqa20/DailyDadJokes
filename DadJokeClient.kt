package com.example.dailydadjokes

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DadJokeClient {
    val instance: DadJokeService by lazy {
        Retrofit.Builder()
            .baseUrl("https://icanhazdadjoke.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DadJokeService::class.java)

    }


}