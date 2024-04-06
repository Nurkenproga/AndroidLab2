package com.example.apilab.network

import com.example.apilab.model.Animal
import retrofit2.Call
import retrofit2.http.GET

interface AnimalService {


    @GET("v1/animals?name=snake")
    fun fetchAnimalList(): Call<List<Animal>>
}