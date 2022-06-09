package com.example.pruebasamo.implementations

import android.content.Context
import com.example.pruebasamo.interfaces.IMoviesAndSeriesRepository
import com.example.pruebasamo.models.Movie
import com.example.pruebasamo.network.IMoviesAPI

class MoviesAndSeriesRepositoryApiImpl(private val moviesAPI: IMoviesAPI, private val context: Context) : IMoviesAndSeriesRepository {

    override suspend fun getPlayingNowMovies(): List<Movie> {
        val result = moviesAPI.getPlayingNowMovies()
        val movies = result.body()?.results ?: listOf()
        getVideosFromMovieList(movies)
        return movies
    }

    private suspend fun getVideosFromMovieList(movies: List<Movie>) {
        movies.forEach { movie ->
            movie.id?.let {
                val result = moviesAPI.getVideosByID(it)
                val videos = result.body()
                if (videos != null){
                    if (!videos.results.isNullOrEmpty()){
                        movie.video_url = videos.results.first { video -> video.type == "Trailer" }.key
                    }
                }
            }
        }
    }

    override suspend fun getMostPopularMovies(): List<Movie> {
        val result = moviesAPI.getPopularMovies()
        val movies = result.body()?.results ?: listOf()
        getVideosFromMovieList(movies)
        return movies
    }
}