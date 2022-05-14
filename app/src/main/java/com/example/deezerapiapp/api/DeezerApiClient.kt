package com.example.deezerapiapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


class DeezerApiClient {

    companion object{
        private var deezerApi: DeezerApi? = null

        fun getDeezerApi(): DeezerApi? {

            if(deezerApi == null){
                deezerApi = Retrofit.Builder()
                    .baseUrl("http://api.deezer.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(DeezerApi::class.java)
            }

            return deezerApi
        }
    }


}