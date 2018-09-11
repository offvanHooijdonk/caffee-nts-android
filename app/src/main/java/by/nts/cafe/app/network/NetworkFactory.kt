package by.nts.cafe.app.network

import by.nts.cafe.app.helper.getPref
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Yahor_Fralou on 9/11/2018 11:40 AM.
 */

class NetworkFactory {
    companion object {
        private val url: String by lazy {
            val url: String = getPref().getEndpointHost() + getPref().getEndpointPath()
            if (!url.endsWith("/")) "$url/" else url
        }
        private val httpClient: OkHttpClient by lazy {
            OkHttpClient.Builder()
                    .readTimeout(10, TimeUnit.SECONDS)
                    .connectTimeout(10, TimeUnit.SECONDS)
                    //.addNetworkInterceptor(authenticationInterceptor)
                    .build()
        }
        val retrofit: Retrofit by lazy {
            Retrofit.Builder()
                    .baseUrl(url)
                    .client(httpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
        }
    }
}