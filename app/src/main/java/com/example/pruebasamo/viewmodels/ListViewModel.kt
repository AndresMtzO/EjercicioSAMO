package com.example.pruebasamo.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pruebasamo.interfaces.IMoviesAndSeriesRepository
import com.example.pruebasamo.models.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListViewModel (private val moviesRepository: IMoviesAndSeriesRepository) : ViewModel()  {

    val movieList : MutableState<List<Movie>> = mutableStateOf(listOf())

    init {
        getMovieListByCategory(false)
    }

    fun getMovieListByCategory(isPlayingNow: Boolean){
        viewModelScope.launch(Dispatchers.IO) {
            val currentList =
                if (isPlayingNow) moviesRepository.getPlayingNowMovies() else moviesRepository.getMostPopularMovies()
            movieList.value = currentList
        }
    }
}

