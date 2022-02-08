package app.iandis.camelmovies.core.network

import app.iandis.camelmovies.core.env.EnvConfig
import okhttp3.*

class AppHttpClient : OkHttpClient() {
    private val _interceptorForApiKey: Interceptor = Interceptor { chain ->
        val request: Request.Builder = chain.request().newBuilder()
        val originalHttpUrl: HttpUrl = chain.request().url()
        val interceptedUrl: HttpUrl =
            originalHttpUrl.newBuilder().addQueryParameter("api_key", EnvConfig.API_KEY).build()
        request.url(interceptedUrl)

        return@Interceptor chain.proceed(request.build())
    }

    private val _interceptors: List<Interceptor> by lazy {
        super.interceptors() + _interceptorForApiKey
    }

    override fun interceptors(): List<Interceptor> = _interceptors
}