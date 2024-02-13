package com.zhudaiweather.android.logic.network

import PlaceResponse
import com.zhudaiweather.android.ZHUDAIWeatherApplication
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaceService {
    @GET("v2/place?token=${ZHUDAIWeatherApplication.token}&lang=zh_CN")
    fun searchPlaces(@Query("query") query: String): Call<PlaceResponse>
}