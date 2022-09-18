package com.karasu.yome.di

import com.google.gson.GsonBuilder
import com.karasu.yome.network.api.AccountApi
import com.karasu.yome.network.api.ImageApi
import com.karasu.yome.network.api.LibraryApi
import com.karasu.yome.network.api.SeriesApi
import com.karasu.yome.service.ServerService
import com.karasu.yome.service.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(
        authInterceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(authInterceptor).build()
    }

    @Singleton
    @Provides
    fun providesAuthInterceptor() = Interceptor { chain ->
        var original = chain.request()

        // The user can changed the server Url
        val baseUrl = HttpUrl.parse(ServerService.getUrl())!!
        val newUrl = original.url().newBuilder()
            .scheme(baseUrl.scheme())
            .host(baseUrl.host())
            .encodedPath(baseUrl.encodedPath() + original.url().encodedPath())
            .build()
        original = original.newBuilder()
            .url(newUrl)
            .build()

        // Adding the token
        var builder = original.newBuilder()
        val isAuthRequest = original.url().encodedPath().contains("authenticate")
        if (!isAuthRequest) {
            val token = UserService.token
            builder.header("Authorization", "Bearer $token")
        }
        val request = builder.build()

        println("Request ${request.method()} @ ${request.url()}")

        // handle errors
        val response = chain.proceed(request)
        val code = response.code()
        // val body = Json.encodeToJsonElement(response.body()?.string()) // create a type for yome server errors
        // println(body.toString())

        if (!response.isSuccessful) throw IOException("$code")

        response
    }

    @Provides
    @Singleton
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
        return getDynamicBuilder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }

    @Provides
    @Singleton
    fun provideSeriesApiService(retrofit: Retrofit): SeriesApi {
        return retrofit.create(SeriesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAccountApiService(retrofit: Retrofit): AccountApi {
        return retrofit.create(AccountApi::class.java)
    }

    @Provides
    @Singleton
    fun provideLibraryApiService(retrofit: Retrofit): LibraryApi {
        return retrofit.create(LibraryApi::class.java)
    }

    @Provides
    @Singleton
    fun provideImageApiService(retrofit: Retrofit): ImageApi {
        return retrofit.create(ImageApi::class.java)
    }
}

fun getDynamicBuilder(): Retrofit.Builder {
    return Retrofit.Builder().baseUrl(ServerService.getUrl())
}