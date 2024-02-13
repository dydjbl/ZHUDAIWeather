package com.zhudaiweather.android.logic

import Place
import android.util.Log
import androidx.lifecycle.liveData
import com.zhudaiweather.android.logic.network.ZHUDAIWeatherNetwork
import kotlinx.coroutines.Dispatchers
import java.lang.Exception
import java.lang.RuntimeException


object Repository {
    fun searchPlaces(query:String) = liveData(Dispatchers.IO) {
        val result = try {
            Log.d("search_place",query)
            val placeResponse = ZHUDAIWeatherNetwork.searchPlaces(query)
            if(placeResponse!=null){
                val places = placeResponse.places
                Result.success(places)
            }else{
                Result.failure(RuntimeException("response status is ${placeResponse.status}"))
            }
        }catch (e:Exception){
            Result.failure<List<Place>>(e)
        }
        emit(result)
    }
}