package com.example.pruebasamo.implementations

import com.example.pruebasamo.models.Movie
import com.example.pruebasamo.interfaces.IMoviesAndSeriesRepository
import com.example.pruebasamo.network.IMoviesAPI

class MoviesAndSeriesRepositoryApiImpl(private val moviesAPI: IMoviesAPI) : IMoviesAndSeriesRepository {

    override suspend fun getPlayingNowMovies(): List<Movie> {
        val result = moviesAPI.getPlayingNowMovies()
        return result.body()?.results ?: listOf()
    }

    override suspend fun getMostPopularMovies(): List<Movie> {
        val result = moviesAPI.getPopularMovies()
        return result.body()?.results ?: listOf()
    }
}