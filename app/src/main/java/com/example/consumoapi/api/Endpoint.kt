package com.example.consumoapi.api

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Endpoint {
    @GET("api/breed/{breed}/images/random")
    fun getDog(@Path(value = "breed") breed : String) : Call<JsonObject>



}