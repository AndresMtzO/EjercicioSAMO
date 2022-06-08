package com.example.pruebasamo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.pruebasamo.interfaces.IMoviesAndSeriesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.activityScope

class ListActivity : AppCompatActivity() {

    val moviesRepository by inject<IMoviesAndSeriesRepository>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        lifecycleScope.launch(Dispatchers.IO){
            var list = moviesRepository.getPlayingNowMovies()
            list.forEach { item ->
                Log.d("Movie playing now", item.title ?: "")
            }

            list = moviesRepository.getMostPopularMovies()
            list.forEach { item ->
                Log.d("Movie popular", item.title ?: "")
            }
        }
    }

}