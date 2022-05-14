package com.example.deezerapiapp.api

import com.example.deezerapiapp.models.Playlist
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DeezerApi {

    @GET("playlist/{id}")
    fun getPlaylistById(@Path("id") id: String): Call<Playlist>
}