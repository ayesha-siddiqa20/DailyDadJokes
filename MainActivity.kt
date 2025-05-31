package com.example.dailydadjokes

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var quoteText : TextView
    lateinit var authorText : TextView
    lateinit var button : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        quoteText = findViewById(R.id.quoteText)
        authorText = findViewById(R.id.authorText)
        button = findViewById(R.id.refreshButton)

        fetchDadQuote()

        button.setOnClickListener {
            fetchDadQuote()
        }



    }

    private fun fetchDadQuote() {
        DadJokeClient.instance.getRandomQuote().enqueue(object : Callback<DadJoke> {
            override fun onResponse(call: Call<DadJoke>, response: Response<DadJoke>) {
                if (response.isSuccessful && response.body() != null) {
                    val quote = response.body()!!
                    quoteText.text = "\"${quote.joke}\""
                    authorText.text = "- Dad"
                } else {
                    quoteText.text = "Dad has no more jokes left to give"
                    authorText.text = "- MIA Dad :("
                }
            }

            override fun onFailure(call: Call<DadJoke>, t: Throwable) {
                quoteText.text = "Dad is offline :("
                authorText.text = ""
            }
        })


    }
}