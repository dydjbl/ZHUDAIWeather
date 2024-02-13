package com.zhudaiweather.android.logic.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await
import java.lang.RuntimeException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object ZHUDAIWeatherNetwork {
    //这个是创建retrofit网络通信库的标准写法
    private val placeService = ServiceCreator.create<PlaceService>()
    //创建一个挂起函数，以便于执行相关网络访问的耗时操作,表示该挂起函数等待结果的返回
    suspend fun searchPlaces(query:String) = placeService.searchPlaces(query).await()
    private suspend fun <T> Call<T>.await():T{
        return suspendCoroutine { continuation ->
            //object : Callback<T> 这一行代码的意思是一个实现了Callback的接口
            enqueue(object : Callback<T>{
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if(body!=null)continuation.resume(body)
                    else continuation.resumeWithException(RuntimeException("response body is null"))
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }

}