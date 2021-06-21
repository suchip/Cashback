package com.example.cashback

import com.google.gson.GsonBuilder
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type

/**
 * Created by sampath  on 15/10/2020.
 */
class APIClient_Main {

    companion object {
//        private const val BASE_URL = "https://affiliate-api.flipkart.net/affiliate/"
        private const val BASE_URL_MAIN = "http://65.1.131.44/api/"


        var retofit: Retrofit? = null

        val client: Retrofit
            get() {

                if (retofit == null) {
                    val gson = GsonBuilder()

                    retofit = Retrofit.Builder()
                        .baseUrl(BASE_URL_MAIN)
//                        .addConverterFactory(NullOnEmptyConverterFactory())

                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                }
                return retofit!!
            }

    }

    internal class NullOnEmptyConverterFactory : Converter.Factory() {
        fun responseBody(
            type: Type?,
            annotations: Array<Annotation?>?,
            retrofit: Retrofit
        ): Any {
            val delegate: Converter<ResponseBody, *> =
                retrofit.nextResponseBodyConverter<Any>(this, type, annotations)
            return object {
                fun convert(body: ResponseBody): Any? {
                    return if (body.contentLength() == 0L) null else delegate.convert(body)
                }
            }
        }
    }

}

