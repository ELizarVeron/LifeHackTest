package ru.cft.shift2023winter.data.retrofit

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

private const val TIMEOUT:Long = 30

object RetrofitClient {
    private var retrofit: Retrofit? = null

    fun getClient(baseUrl: String): Retrofit {

        var localRetrofit = retrofit
        if (localRetrofit == null) {
            localRetrofit = newInstance(
                baseUrl,
                createHttpClient(createInterceptor())
            )
        }
        return localRetrofit

    }

    private fun newInstance(baseUrl: String, httpClient: OkHttpClient) : Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(createScalarsFactory())
            .addConverterFactory(createGsonFactory())
            .client(httpClient)
            .build()

    private fun createHttpClient(interceptor:HttpLoggingInterceptor): OkHttpClient =
            OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    private fun createGsonFactory():GsonConverterFactory {
        //  GsonConverterFactory.create()
        val builder = GsonBuilder().disableHtmlEscaping().create()

        return  GsonConverterFactory.create(builder)
    }


    private fun createScalarsFactory(): ScalarsConverterFactory =
       ScalarsConverterFactory.create()



    private fun createInterceptor():HttpLoggingInterceptor{
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }
