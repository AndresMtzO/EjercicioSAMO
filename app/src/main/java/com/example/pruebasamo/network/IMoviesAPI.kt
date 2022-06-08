package com.example.pruebasamo.network

import com.example.pruebasamo.helpers.Constants
import com.example.pruebasamo.models.MovieResult
import retrofit2.Response
import retrofit2.http.GET

interface IMoviesAPI {

    @GET("${Constants.VERSION}movie/now_playing${Constants.API_KEY_KEY}${Constants.API_KEY_VALUE}")
    suspend fun getPlayingNowMovies() : Response<MovieResult>

    @GET("${Constants.VERSION}movie/popular${Constants.API_KEY_KEY}${Constants.API_KEY_VALUE}")
    suspend fun getPopularMovies() : Response<MovieResult>

}