package com.example.pruebasamo

import android.app.Application
import com.example.pruebasamo.helpers.Constants
import com.example.pruebasamo.implementations.MoviesAndSeriesRepositoryApiImpl
import com.example.pruebasamo.interfaces.IMoviesAndSeriesRepository
import com.example.pruebasamo.network.IMoviesAPI
import com.example.pruebasamo.viewmodels.ListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger()
            androidContext(this@MyApplication)
            modules(networkModule, dataModule, viewModelModule)
        }
    }

    private val networkModule = module {
        single { getRetrofit() }
        single { getMoviesApi(get()) }
    }

    private fun getRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getMoviesApi(retrofit: Retrofit) : IMoviesAPI {
        return retrofit.create(IMoviesAPI::class.java)
    }

    private val dataModule = module {
        single<IMoviesAndSeriesRepository> { MoviesAndSeriesRepositoryApiImpl(get(), androidContext()) }
    }

    private val viewModelModule = module{
        viewModel{
            ListViewModel(get())
        }
    }
}