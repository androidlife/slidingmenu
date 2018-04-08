package au.com.sentia.test.network.provider

import com.laaptu.sliding.BuildConfig
import com.laaptu.sliding.network.ApiService
import com.laaptu.sliding.network.URL_BASE
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiManager {
    private val retrofit: Retrofit
    val apiService: ApiService
        get() = retrofit.create(ApiService::class.java)

    init {
        val builder: OkHttpClient.Builder = OkHttpClient.Builder()
        builder.connectTimeout(1, TimeUnit.MINUTES)
        builder.readTimeout(1, TimeUnit.MINUTES)
        if (BuildConfig.DEBUG)
            builder.addInterceptor(getLoggingInterceptor(HttpLoggingInterceptor.Level.BODY))
        retrofit = Retrofit.Builder()
                .baseUrl(URL_BASE)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    private fun getLoggingInterceptor(level: HttpLoggingInterceptor.Level): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = level
        return loggingInterceptor
    }
}
