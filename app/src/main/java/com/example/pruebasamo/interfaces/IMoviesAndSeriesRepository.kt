package com.example.pruebasamo.interfaces

import com.example.pruebasamo.models.Movie

interface IMoviesAndSeriesRepository {
    suspend fun getPlayingNowMovies() : List<Movie>
    suspend fun getMostPopularMovies() : List<Movie>
}