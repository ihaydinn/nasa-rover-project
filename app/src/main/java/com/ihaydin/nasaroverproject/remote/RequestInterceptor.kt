package com.ihaydin.nasaroverproject.remote

import com.ihaydin.nasaroverproject.Constant
import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalHttpUrl = originalRequest.url()

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("api_key", Constant.API_KEY)
            .build()

        val request = originalRequest.newBuilder().url(url).build()

        return chain.proceed(request)
    }
}