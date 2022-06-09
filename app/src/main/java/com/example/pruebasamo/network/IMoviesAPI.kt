package com.example.pruebasamo.network

import com.example.pruebasamo.helpers.Constants
import com.example.pruebasamo.models.MovieResult
import com.example.pruebasamo.models.VideoResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface IMoviesAPI {

    @GET("${Constants.VERSION}/movie/now_playing${Constants.API_KEY_KEY}${Constants.API_KEY_VALUE}${Constants.API_LANGUAGE}${Constants.API_LANGUAGE_IMAGES}")
    suspend fun getPlayingNowMovies() : Response<MovieResult>

    @GET("${Constants.VERSION}/movie/popular${Constants.API_KEY_KEY}${Constants.API_KEY_VALUE}${Constants.API_LANGUAGE}${Constants.API_LANGUAGE_IMAGES}")
    suspend fun getPopularMovies() : Response<MovieResult>

    @GET("${Constants.VERSION}/movie/{id}/videos${Constants.API_KEY_KEY}${Constants.API_KEY_VALUE}")
    suspend fun getVideosByID(@Path("id") id: Int) : Response<VideoResult>
}