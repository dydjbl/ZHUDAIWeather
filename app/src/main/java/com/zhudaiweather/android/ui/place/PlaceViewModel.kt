package com.zhudaiweather.android.ui.place

import Place
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zhudaiweather.android.logic.Repository
import androidx.lifecycle.map
import androidx.lifecycle.switchMap

class PlaceViewModel:ViewModel() {
    private val searchLivedata = MutableLiveData<String>()
    val placeList = ArrayList<Place>()
    //将searchLivedata转换成一个可被观察的对象
    val placeLiveData = searchLivedata.switchMap{
        query ->
        Log.d("change2",query)
        Repository.searchPlaces(query)
    }
    fun searchPlaces(query: String){
        Log.d("change",query)
        searchLivedata.value = query
    }
}