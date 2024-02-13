package com.zhudaiweather.android

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class ZHUDAIWeatherApplication :Application(){


    companion object{
        //提供两个可以全局获取得到的变量，天气查询接口令牌和context上下文对象
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
        const val token = "tSHPbkWNVQCuLp3O"
    }
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}