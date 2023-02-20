package com.example.chefschoice.data.network.remote.service
import com.example.chefschoice.data.network.remote.api.IApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


object ServiceBuilder {

    private const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"


    fun getInstance(): Retrofit {

        //OkHttp will automatically log incoming and outgoing HTTP requests and responses to Logcat
        val logging = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)


        val httpClient = OkHttpClient.Builder()
            //.callTimeout(5,TimeUnit.SECONDS)//Request Timeouts: Handling slow network connections
            .addInterceptor(logging)

        //Moshi helps us to serialize and deserialize the JSON in a better and simpler way.
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()




        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi)).client(httpClient.build())
            .build()
    }






}