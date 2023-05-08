package com.example.apicallingwithcoroutines

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    val logging = HttpLoggingInterceptor()
    val httpClient = OkHttpClient.Builder()

    val api : MyApi by lazy {
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        httpClient.addInterceptor(logging)

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).client(httpClient.build())
            .build()
            .create(MyApi::class.java)
    }
}