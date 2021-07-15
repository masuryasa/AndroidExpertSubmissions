package com.suryasa.made.core.data.source.remote.network

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class ApiInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val httpUrl = original.url
        val newHttpUrl = httpUrl.newBuilder()
            .addQueryParameter("api_key", "97797f9332f1801b95e9c3ef8f142930")
            .build()
        val requestBuilder = original.newBuilder().url(newHttpUrl)
        val request = requestBuilder
                .addHeader("Content-Type", "application/json")
                .addHeader("X-Platform", "Android")
                .build()
        return chain.proceed(request)
    }
}