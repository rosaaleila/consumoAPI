package com.example.consumoapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.consumoapi.api.Endpoint
import com.example.consumoapi.databinding.ActivityMainBinding
import com.google.gson.JsonObject
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSearch.setOnClickListener { getImage() }

    }

    private fun getImage() {

        val url = "https://dog.ceo/"
        val dogUser = binding.editBreed.text.toString()
        val retrofitClient = retrofitInstance(url)
        val endpoint = retrofitClient.create(Endpoint::class.java)

        endpoint.getDog(dogUser).enqueue(object : Callback<JsonObject> {

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                val urlImage = response.body()?.get("message")?.asString
                Picasso.get().load(urlImage).into(binding.imageDog)
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Toast.makeText(applicationContext, "Erro ao acessar", Toast.LENGTH_LONG).show()
            }

        })

//        val urlImage = binding.editBreed.text.toString()
//        Picasso.get().load(urlImage).into(binding.imageDog)
    }

    private fun retrofitInstance(url: String): Retrofit {
        return Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build()
    }
}