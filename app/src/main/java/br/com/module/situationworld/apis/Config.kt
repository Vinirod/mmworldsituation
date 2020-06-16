package br.com.module.situationworld.apis

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Config {
    private const val URL_BASE_SERVICE = "https://disease.sh/v2/"

    private lateinit var mRetrofit: Retrofit

    private val gson = GsonBuilder().create()

    private val mHttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val mOkHttpClientBuilder = OkHttpClient.Builder().addInterceptor(mHttpLoggingInterceptor)
    private val mOkHttpClient = mOkHttpClientBuilder.build()

    fun <T> createService(serviceClass: Class<T>?): T {
        mRetrofit = Retrofit.Builder()
            .client(mOkHttpClient)
            .baseUrl(URL_BASE_SERVICE)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        return mRetrofit.create(serviceClass)
    }
}