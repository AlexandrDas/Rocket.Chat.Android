package chat.rocket.android.helper

import android.content.Context
import chat.rocket.android.RocketChatCache
import chat.rocket.android.api.rest.CookieInterceptor
import chat.rocket.android.api.rest.DefaultCookieProvider
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient

object OkHttpHelper {

    fun getClient(): OkHttpClient {
        if (httpClient == null) {
            httpClient = OkHttpClient()
        }
        return httpClient ?: throw AssertionError("httpClient set to null by another thread")
    }

    fun getClientForUploadFile(): OkHttpClient {
        if (httpClientForUploadFile == null) {
            httpClientForUploadFile = OkHttpClient.Builder().build()
        }
        return httpClientForUploadFile ?: throw AssertionError("httpClientForUploadFile set to null by another thread")
    }

    fun getClientForDownloadFile(): OkHttpClient {
        if(httpClientForDownloadFile == null) {
            httpClientForDownloadFile = OkHttpClient.Builder()
                    .followRedirects(true)
                    .followSslRedirects(true)
                    .addInterceptor(CookieInterceptor(DefaultCookieProvider()))
                    .build()
        }
        return httpClientForDownloadFile ?: throw  AssertionError("httpClientForDownloadFile set to null by another thread")
    }
    /**
     * Returns the OkHttpClient instance for WebSocket connection.
     * @return The OkHttpClient WebSocket connection instance.
     */
    fun getClientForWebSocket(): OkHttpClient {
        if (httpClientForWS == null) {
            httpClientForWS = OkHttpClient.Builder()
                    .readTimeout(0, TimeUnit.NANOSECONDS)
                    .build()
        }
        return httpClientForWS ?: throw AssertionError("httpClientForWS set to null by another thread")
    }

    private var httpClient: OkHttpClient? = null
    private var httpClientForUploadFile: OkHttpClient? = null
    private var httpClientForDownloadFile: OkHttpClient? = null
    private var httpClientForWS: OkHttpClient? = null
}
